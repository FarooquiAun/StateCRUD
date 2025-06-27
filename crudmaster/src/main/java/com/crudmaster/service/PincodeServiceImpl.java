package com.crudmaster.service;

import com.crudmaster.dto.pincode.*;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.PincodeEntity;
import com.crudmaster.entity.StateEntity;
import com.crudmaster.mapper.PincodeMapper;
import com.crudmaster.repository.CityRepo;
import com.crudmaster.repository.PincodeRepo;
import com.crudmaster.repository.StateRepo;
import com.crudmaster.specification.pincodespecification.PincodeSpecification;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PincodeServiceImpl implements PincodeService{

    private PincodeRepo pincodeRepo;
    private PincodeMapper pincodeMapper;
    private CityRepo cityRepo;
    private StateRepo stateRepo;

    public PincodeServiceImpl(PincodeRepo pincodeRepo, PincodeMapper pincodeMapper, CityRepo cityRepo,StateRepo stateRepo) {
        this.pincodeRepo = pincodeRepo;
        this.pincodeMapper=pincodeMapper;
        this.cityRepo = cityRepo;
        this.stateRepo=stateRepo;
    }

    @Override
    public PincodeDto addPincode(PincodeCreateDto pincodeCreateDto) {
        CityEntity cityEntity=cityRepo.findById(pincodeCreateDto.getCityId()).orElseThrow(
                ()->new RuntimeException("Id not found...")
        );
        PincodeEntity pincodeEntity=pincodeMapper.pincodeCreateDtoToPincodeDto(pincodeCreateDto);
        pincodeEntity.setCityEntity(cityEntity);
        pincodeRepo.save(pincodeEntity);
        PincodeDto pincodeDto=pincodeMapper.pincodeEntityTopincodeDto(pincodeEntity);
        return pincodeDto;
    }

    @Override
    public List<PincodeDto> getPincode(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<PincodeEntity> page = pincodeRepo.findByDeletedAtNull(pageRequest);
        List<PincodeDto> content = page.stream()
                .map(entity -> pincodeMapper.pincodeEntityTopincodeDto(entity)) // or use MapStruct
                .collect(Collectors.toList());
        return content;
    }

    @Override
    public PincodeDto updatePincode(Long pincodeId, PincodeUpdateDto pincodeUpdateDto) {
        PincodeEntity pincodeEntity=pincodeRepo.findById(pincodeId).orElseThrow(
                ()-> new RuntimeException("pincode Id not found...")
        );
//        if(pincodeDto.getPinCode()!=null){
//               pincodeEntity.setPincode(pincodeDto.getPinCode());
//        }
//        if(pincodeDto.getStatus()!=null){
//              pincodeEntity.setStatus(pincodeDto.getStatus());
//        }
//        if(pincodeDto.getCityId()!=null) {
//            CityEntity cityEntity = cityRepo.findById(pincodeDto.getCityId()).orElseThrow(
//                    () -> new RuntimeException("city Id not found")
//
//            );
//            pincodeEntity.setCityEntity(cityEntity);
//        }
            pincodeEntity.setPincode(pincodeUpdateDto.getPincode());
            pincodeMapper.pincodeUpdateDtoToPincodeDto(pincodeUpdateDto);
            pincodeRepo.save(pincodeEntity);

        return pincodeMapper.pincodeEntityTopincodeDto(pincodeEntity);
    }

    @Override
    public String deletePincode(Long pincodeId) {
        PincodeEntity pincodeEntity=pincodeRepo.findById(pincodeId).orElseThrow(
                ()->new RuntimeException("pincode Id not found")
        );
        pincodeRepo.delete(pincodeEntity);
        return "Deletion done successfully";
    }

    @Override
    public Page<PincodeFilterReturnDto> searchPincode(PincodeFilterDto filterDto) {
        Specification<PincodeEntity> spec= PincodeSpecification.buildSpecification(filterDto);

        Sort.Direction direction=filterDto.getSortDir().equalsIgnoreCase("asc") ?
                 Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable=PageRequest.of(filterDto.getPage(),filterDto.getSize()
        ,Sort.by(direction,filterDto.getSortBy()));
        return pincodeRepo.findAll(spec,pageable).map(pincodeMapper::pincodeToFilterReturnDto);
    }

    @Override
    public String importPincodes(MultipartFile file) {
        try(XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream())){
            XSSFSheet sheet=workbook.getSheetAt(0);
            for (Row row: sheet){
                if (row.getRowNum()==0) {
                    continue;
                }
                Long pincode=(long)row.getCell(0).getNumericCellValue();
                String cityName=row.getCell(1).getStringCellValue().trim();
                String stateName=row.getCell(2).getStringCellValue().trim();

                if (pincode==null || cityName.isEmpty() || stateName.isEmpty()){
                    throw  new RuntimeException("Invalid data in row"+(row.getRowNum()+1));
                }
                StateEntity state=stateRepo.findByStateNameIgnoreCase(stateName).orElseThrow(
                        ()-> new RuntimeException("State  not found"+stateName)
                );
                CityEntity city=cityRepo.findByCityNameIgnoreCaseAndStateEntity(cityName,state).orElseThrow(
                        ()-> new RuntimeException("City not found "+ cityName)
                );
                boolean exists=pincodeRepo.existsByPincodeAndCityEntity(pincode,city);
                if (!exists){
                    PincodeEntity pincodeEntity=new PincodeEntity();
                    pincodeEntity.setPincode(pincode);
                    pincodeEntity.setCityEntity(city);
                    pincodeRepo.save(pincodeEntity);
                }


            }

        }catch (Exception e){
            throw new  RuntimeException(e);
        }
        return "Pincode Data imported Successfully";
    }


}

 
package com.crudmaster.service;

import com.crudmaster.dto.city.*;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.StateEntity;
import com.crudmaster.mapper.CityMapper;
import com.crudmaster.repository.CityRepo;
import com.crudmaster.repository.StateRepo;
import com.crudmaster.specification.cityspecification.CitySpecification;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    private CityRepo cityRepo;
    private CityMapper cityMapper;
    private StateRepo stateRepo;

    public CityServiceImpl(CityRepo cityRepo, CityMapper cityMapper, StateRepo stateRepo) {
        this.cityRepo = cityRepo;
        this.cityMapper = cityMapper;
        this.stateRepo = stateRepo;
    }

    @Override
    public CityDto addCity(CityCreateDto cityCreateDto) {
        StateEntity state = stateRepo.findById(cityCreateDto.getStateId())
                .orElseThrow(() -> new RuntimeException("State not found"));
        CityEntity cityEntity = cityMapper.cityCreateDtoToCity(cityCreateDto);
        cityEntity.setStateEntity(state);
        CityEntity city=cityRepo.save(cityEntity);
        return cityMapper.cityToCityDto(city) ;
    }

    @Override
    public List<CityDto>  getCity(Integer pageNumber,Integer pageSize) {
        PageRequest p=PageRequest.of(pageNumber,pageSize);
        Page<CityEntity> page = cityRepo.findByDeletedAtNull(p);
        List<CityDto> content = page.stream()
                .map(entity -> cityMapper.cityToCityDto(entity)) // or use MapStruct
                .collect(Collectors.toList());
        return content;
    }

    @Override
    public CityDto updateCity(Long cityId, CityUpdateDto cityUpdateDto) {

      CityEntity cityEntity= cityRepo.findById(cityId).orElseThrow(
                ()-> new RuntimeException("CityId not found...")
      );
          cityEntity.setCityName(cityUpdateDto.getCityName());
          cityMapper.cityUpdateDtoToCity(cityUpdateDto);
//        if (cityDto.getCityName() != null)
//            cityEntity.setCityName(cityDto.getCityName());
//
//        if (cityDto.getStatus() != null)
//            cityDto.setStatus(cityDto.getStatus());
//        if(cityDto.getStateId()!=null){
//            StatesEntity states=statesRepo.findById(cityDto.getStateId()).orElseThrow(
//                    ()-> new RuntimeException("State not found")
//            );
//                   cityEntity.setStatesEntity(states);
//        }
        cityRepo.save(cityEntity);
        return  cityMapper.cityToCityDto(cityEntity);
    }

    @Override
    public String deleteCity(Long cityId) {
        CityEntity cityEntity=cityRepo.findById(cityId).orElseThrow(
                ()-> new RuntimeException("CityId Not found...")
                );
             cityRepo.delete(cityEntity);
        return "Deleteion done successfullly";
    }

    @Override
    public Boolean patchCity(Long cityId, CityDto cityDto) {
        CityEntity cityEntity= cityRepo.findById(cityId).orElseThrow(
                ()-> new RuntimeException("CityId not found...")
        );
           cityEntity.setStatus(cityDto.getStatus());
           cityRepo.save(cityEntity);
        return true;
    }

    @Override
    public Page<CityFilterReturnDto> searchCity(CityFilterDto cityFilterDto) {
        Specification<CityEntity> spec= CitySpecification.buildSpecification(cityFilterDto);
        Sort.Direction direction=cityFilterDto.getSortDir().equalsIgnoreCase("asc")?Sort.Direction.ASC:Sort.Direction.DESC;
        Pageable pageable=PageRequest.of(cityFilterDto.getPage(),cityFilterDto.getSize(),Sort.by(direction,cityFilterDto.getSortBy()));

        return cityRepo.findAll(spec,pageable).map(cityMapper::cityToCityReturnDto);
    }

    @Override
    public String importCity(MultipartFile file) {
        try(XSSFWorkbook workbook =new XSSFWorkbook(file.getInputStream())){
            XSSFSheet sheet=workbook.getSheetAt(0);
            for (Row row:sheet){
                if (row.getRowNum()==0){
                    continue;

                }
                String cityName=row.getCell(0).getStringCellValue().trim();
                String stateName=row.getCell(1).getStringCellValue().trim();
                if (stateName.isEmpty() || cityName.isEmpty()){
                    throw new RuntimeException("invalid data in row "+row.getRowNum()+1);
                }
                StateEntity state=stateRepo.findByStateNameIgnoreCase(stateName).orElseThrow(
                        ()   -> new RuntimeException("State not found exception")
                );
                cityRepo.findByCityNameIgnoreCaseAndStateEntity(cityName,state).orElseGet(
                        ()->{
                            CityEntity city=new CityEntity();
                            city.setCityName(cityName);
                            city.setStateEntity(state);
                           return cityRepo.save(city);

                        });

            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return "City import Done succesfully";
    }
}    

package com.crudmaster.service;

import com.crudmaster.dto.city.*;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.StateEntity;
import com.crudmaster.mapper.CityMapper;
import com.crudmaster.repository.CityRepo;
import com.crudmaster.repository.StateRepo;
import com.crudmaster.specification.cityspecification.CitySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
}    

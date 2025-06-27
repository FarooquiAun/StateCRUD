package com.crudmaster.mapper;

import com.crudmaster.dto.city.CityCreateDto;
import com.crudmaster.dto.city.CityDto;
import com.crudmaster.dto.city.CityFilterReturnDto;
import com.crudmaster.dto.city.CityUpdateDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.StateEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CityMapper {


    CityEntity cityDtoToCity(CityDto cityDto);

   // @Mapping(target="statesEntity.stateId",source = "statesEntity")
    CityDto cityToCityDto(CityEntity cityEntity);


    CityEntity cityCreateDtoToCity(CityCreateDto cityCreateDto);

//    @AfterMapping
//    default void setStatesEntity(@MappingTarget CityEntity cityEntity, CityCreateDto dto) {
//        if (dto.getStateId() != null) {
//            StateEntity state = new StateEntity();
//            state.setStateId(dto.getStateId());
//            cityEntity.setStatesEntity(state);
//        } else {
//            cityEntity.setStatesEntity(null);
//        }
//    }

//    CityEntity cityCreateDtoToCity(CityCreateDto cityCreateDto);

    CityEntity cityUpdateDtoToCity(CityUpdateDto cityUpdateDto);

    CityFilterReturnDto cityToCityReturnDto(CityEntity cityEntity);
}

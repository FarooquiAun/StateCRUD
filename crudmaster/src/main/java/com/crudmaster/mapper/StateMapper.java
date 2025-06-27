package com.crudmaster.mapper;

import com.crudmaster.dto.state.StateDto;
import com.crudmaster.dto.state.StateFilterReturnDto;
import com.crudmaster.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StateMapper {
    StateMapper INSTANCE= Mappers.getMapper(StateMapper.class);
    StateDto stateToStateDTO(StateEntity states);
    StateEntity stateDTOToState(StateDto stateDto);
    StateFilterReturnDto stateToFilterReturnDto(StateEntity stateEntity);
}

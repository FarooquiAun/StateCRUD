package com.crudmaster.mapper;

import com.crudmaster.dto.state.StateDto;
import com.crudmaster.dto.state.StateFilterReturnDto;
import com.crudmaster.entity.StateEntity;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-02T11:23:05+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class StateMapperImpl implements StateMapper {

    @Override
    public StateDto stateToStateDTO(StateEntity states) {
        if ( states == null ) {
            return null;
        }

        StateDto stateDto = new StateDto();

        stateDto.setStateName( states.getStateName() );
        stateDto.setStatus( states.getStatus() );

        return stateDto;
    }

    @Override
    public StateEntity stateDTOToState(StateDto stateDto) {
        if ( stateDto == null ) {
            return null;
        }

        StateEntity stateEntity = new StateEntity();

        stateEntity.setStateName( stateDto.getStateName() );
        if ( stateDto.getStatus() != null ) {
            stateEntity.setStatus( stateDto.getStatus() );
        }

        return stateEntity;
    }

    @Override
    public StateFilterReturnDto stateToFilterReturnDto(StateEntity stateEntity) {
        if ( stateEntity == null ) {
            return null;
        }

        Long stateId = null;
        String stateName = null;
        LocalDateTime createdAt = null;
        Character status = null;

        stateId = stateEntity.getStateId();
        stateName = stateEntity.getStateName();
        createdAt = stateEntity.getCreatedAt();
        status = stateEntity.getStatus();

        StateFilterReturnDto stateFilterReturnDto = new StateFilterReturnDto( stateId, stateName, createdAt, status );

        return stateFilterReturnDto;
    }
}

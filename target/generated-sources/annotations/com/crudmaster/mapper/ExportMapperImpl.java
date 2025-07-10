package com.crudmaster.mapper;

import com.crudmaster.dto.export.CityExportDto;
import com.crudmaster.dto.export.PincodeExportDto;
import com.crudmaster.dto.export.StateExportDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.PincodeEntity;
import com.crudmaster.entity.StateEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-02T11:23:04+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class ExportMapperImpl implements ExportMapper {

    @Override
    public StateExportDto toStateDTO(StateEntity state) {
        if ( state == null ) {
            return null;
        }

        StateExportDto stateExportDto = new StateExportDto();

        stateExportDto.setId( state.getStateId() );
        stateExportDto.setCreatedAt( state.getCreatedAt() );
        stateExportDto.setStateName( state.getStateName() );
        stateExportDto.setStatus( state.getStatus() );

        stateExportDto.setCities( new java.util.ArrayList<>() );

        return stateExportDto;
    }

    @Override
    public CityExportDto toCityDTO(CityEntity city) {
        if ( city == null ) {
            return null;
        }

        CityExportDto cityExportDto = new CityExportDto();

        cityExportDto.setId( city.getCityId() );
        cityExportDto.setCityName( city.getCityName() );
        cityExportDto.setCreatedAt( city.getCreatedAt() );
        cityExportDto.setStatus( city.getStatus() );

        cityExportDto.setPincodes( new java.util.ArrayList<>() );

        return cityExportDto;
    }

    @Override
    public PincodeExportDto toPincodeDTO(PincodeEntity pin) {
        if ( pin == null ) {
            return null;
        }

        PincodeExportDto pincodeExportDto = new PincodeExportDto();

        pincodeExportDto.setId( pin.getPincodeId() );
        pincodeExportDto.setCreatedAt( pin.getCreatedAt() );
        pincodeExportDto.setPincode( (int) pin.getPincode() );
        pincodeExportDto.setStatus( pin.getStatus() );

        return pincodeExportDto;
    }
}

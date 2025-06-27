package com.crudmaster.mapper;

import com.crudmaster.dto.pincode.PincodeCreateDto;
import com.crudmaster.dto.pincode.PincodeDto;
import com.crudmaster.dto.pincode.PincodeFilterReturnDto;
import com.crudmaster.dto.pincode.PincodeUpdateDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.PincodeEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T15:18:13+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class PincodeMapperImpl implements PincodeMapper {

    @Override
    public PincodeEntity pincodeDtoTopincodeEntity(PincodeDto pincodeDto) {
        if ( pincodeDto == null ) {
            return null;
        }

        PincodeEntity pincodeEntity = new PincodeEntity();

        if ( pincodeDto.getPincode() != null ) {
            pincodeEntity.setPincode( pincodeDto.getPincode() );
        }
        if ( pincodeDto.getStatus() != null ) {
            pincodeEntity.setStatus( pincodeDto.getStatus() );
        }

        return pincodeEntity;
    }

    @Override
    public PincodeDto pincodeEntityTopincodeDto(PincodeEntity pincodeEntity) {
        if ( pincodeEntity == null ) {
            return null;
        }

        PincodeDto pincodeDto = new PincodeDto();

        pincodeDto.setCity( pincodeEntity.getCityEntity() );
        pincodeDto.setPincode( pincodeEntity.getPincode() );
        pincodeDto.setStatus( pincodeEntity.getStatus() );

        return pincodeDto;
    }

    @Override
    public PincodeEntity pincodeCreateDtoToPincodeDto(PincodeCreateDto pincodeCreateDto) {
        if ( pincodeCreateDto == null ) {
            return null;
        }

        PincodeEntity pincodeEntity = new PincodeEntity();

        if ( pincodeCreateDto.getPincode() != null ) {
            pincodeEntity.setPincode( pincodeCreateDto.getPincode() );
        }
        if ( pincodeCreateDto.getStatus() != null ) {
            pincodeEntity.setStatus( pincodeCreateDto.getStatus() );
        }

        return pincodeEntity;
    }

    @Override
    public PincodeEntity pincodeUpdateDtoToPincodeDto(PincodeUpdateDto pincodeUpdateDto) {
        if ( pincodeUpdateDto == null ) {
            return null;
        }

        PincodeEntity pincodeEntity = new PincodeEntity();

        if ( pincodeUpdateDto.getPincode() != null ) {
            pincodeEntity.setPincode( pincodeUpdateDto.getPincode() );
        }

        return pincodeEntity;
    }

    @Override
    public PincodeFilterReturnDto pincodeToFilterReturnDto(PincodeEntity pincodeEntity) {
        if ( pincodeEntity == null ) {
            return null;
        }

        CityEntity city = null;
        Long pincode = null;
        Character status = null;

        city = pincodeEntity.getCityEntity();
        pincode = pincodeEntity.getPincode();
        status = pincodeEntity.getStatus();

        PincodeFilterReturnDto pincodeFilterReturnDto = new PincodeFilterReturnDto( pincode, city, status );

        return pincodeFilterReturnDto;
    }
}

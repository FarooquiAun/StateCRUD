package com.crudmaster.mapper;

import com.crudmaster.dto.city.CityCreateDto;
import com.crudmaster.dto.city.CityDto;
import com.crudmaster.dto.city.CityFilterReturnDto;
import com.crudmaster.dto.city.CityUpdateDto;
import com.crudmaster.entity.CityEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T15:18:13+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public CityEntity cityDtoToCity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        cityEntity.setCityName( cityDto.getCityName() );
        if ( cityDto.getStatus() != null ) {
            cityEntity.setStatus( cityDto.getStatus() );
        }
        cityEntity.setStateEntity( cityDto.getStateEntity() );

        return cityEntity;
    }

    @Override
    public CityDto cityToCityDto(CityEntity cityEntity) {
        if ( cityEntity == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setCityName( cityEntity.getCityName() );
        cityDto.setStatus( cityEntity.getStatus() );
        cityDto.setStateEntity( cityEntity.getStateEntity() );

        return cityDto;
    }

    @Override
    public CityEntity cityCreateDtoToCity(CityCreateDto cityCreateDto) {
        if ( cityCreateDto == null ) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        cityEntity.setCityName( cityCreateDto.getCityName() );
        if ( cityCreateDto.getStatus() != null ) {
            cityEntity.setStatus( cityCreateDto.getStatus() );
        }

        return cityEntity;
    }

    @Override
    public CityEntity cityUpdateDtoToCity(CityUpdateDto cityUpdateDto) {
        if ( cityUpdateDto == null ) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        cityEntity.setCityName( cityUpdateDto.getCityName() );

        return cityEntity;
    }

    @Override
    public CityFilterReturnDto cityToCityReturnDto(CityEntity cityEntity) {
        if ( cityEntity == null ) {
            return null;
        }

        CityFilterReturnDto cityFilterReturnDto = new CityFilterReturnDto();

        cityFilterReturnDto.setCityId( cityEntity.getCityId() );
        cityFilterReturnDto.setCityName( cityEntity.getCityName() );
        cityFilterReturnDto.setStateEntity( cityEntity.getStateEntity() );

        return cityFilterReturnDto;
    }
}

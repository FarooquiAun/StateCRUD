package com.crudmaster.mapper;

import com.crudmaster.dto.export.CityExportDto;
import com.crudmaster.dto.export.PincodeExportDto;
import com.crudmaster.dto.export.StateExportDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.PincodeEntity;
import com.crudmaster.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExportMapper {

        @Mapping(source = "stateId", target = "id")
        @Mapping(target = "cities", expression = "java(new java.util.ArrayList<>())")
        StateExportDto toStateDTO(StateEntity state);

        @Mapping(source = "cityId", target = "id")
        @Mapping(target = "pincodes", expression = "java(new java.util.ArrayList<>())")
        CityExportDto toCityDTO(CityEntity city);

        @Mapping(source = "pincodeId", target = "id")
        PincodeExportDto toPincodeDTO(PincodeEntity pin);



}

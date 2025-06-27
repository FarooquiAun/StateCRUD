package com.crudmaster.mapper;

import com.crudmaster.dto.pincode.PincodeCreateDto;
import com.crudmaster.dto.pincode.PincodeDto;
import com.crudmaster.dto.pincode.PincodeFilterReturnDto;
import com.crudmaster.dto.pincode.PincodeUpdateDto;
import com.crudmaster.entity.PincodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PincodeMapper {

    PincodeEntity pincodeDtoTopincodeEntity(PincodeDto pincodeDto);
    @Mapping(source = "cityEntity", target = "city")
    PincodeDto pincodeEntityTopincodeDto(PincodeEntity pincodeEntity);

    PincodeEntity pincodeCreateDtoToPincodeDto(PincodeCreateDto pincodeCreateDto);
    PincodeEntity pincodeUpdateDtoToPincodeDto(PincodeUpdateDto pincodeUpdateDto);
    @Mapping(source = "cityEntity", target = "city")
//    @Mapping(source = "cityEntity.statesEntity.stateName", target = "stateName")
    PincodeFilterReturnDto pincodeToFilterReturnDto(PincodeEntity pincodeEntity);
}

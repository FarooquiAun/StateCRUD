package com.crudmaster.service;

import com.crudmaster.dto.pincode.*;
import com.crudmaster.entity.PincodeEntity;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PincodeService {

   PincodeDto addPincode(PincodeCreateDto pincodeCreateDto);
   List<PincodeDto> getPincode(Integer pageNumber,Integer pageSize);
   PincodeDto updatePincode(Long pincodeId, PincodeUpdateDto pincodeUpdateDto);
   String deletePincode(Long pincodeId);
   Page<PincodeFilterReturnDto> searchPincode(PincodeFilterDto pincodeFilterDto);

}

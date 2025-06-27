package com.crudmaster.service;

import com.crudmaster.dto.pincode.*;
import com.crudmaster.entity.PincodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface PincodeService {

   PincodeDto addPincode(PincodeCreateDto pincodeCreateDto);
   List<PincodeDto> getPincode(Integer pageNumber,Integer pageSize);
   PincodeDto updatePincode(Long pincodeId, PincodeUpdateDto pincodeUpdateDto);
   String deletePincode(Long pincodeId);
   Page<PincodeFilterReturnDto> searchPincode(PincodeFilterDto pincodeFilterDto);
   String importPincodes(MultipartFile file);

}

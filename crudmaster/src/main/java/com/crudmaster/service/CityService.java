package com.crudmaster.service;

import com.crudmaster.dto.city.*;
import com.crudmaster.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;


public interface CityService  {

    CityDto addCity(CityCreateDto cityCreateDto);
    List<CityDto> getCity(Integer pageNumber,Integer pageSize);
    CityDto updateCity(Long cityId, CityUpdateDto cityUpdateDto);
    String deleteCity(Long cityId);
    Boolean patchCity(Long cityId,CityDto cityDto);
    Page<CityFilterReturnDto> searchCity(CityFilterDto cityFilterDto);
    String importCity(MultipartFile file);
}

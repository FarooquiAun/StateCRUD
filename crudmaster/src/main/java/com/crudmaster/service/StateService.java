package com.crudmaster.service;


import com.crudmaster.dto.state.StateDto;
import com.crudmaster.dto.state.StateFilterDto;
import com.crudmaster.dto.state.StateFilterReturnDto;
import com.crudmaster.entity.StateEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StateService {
    StateDto saveAll(StateDto stateDto);
    List<StateDto> getAll(Integer pageNumber, Integer pageSize);
    Boolean updateState(Long stateId,StateDto stateDto);
    Boolean patchState(Long stateId,StateDto stateDto);
    String deleteState(Long stateId);
    Page<StateFilterReturnDto> searchState(StateFilterDto stateFilterDto);
    String importStates(MultipartFile file);

}

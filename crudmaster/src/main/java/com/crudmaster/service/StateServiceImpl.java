package com.crudmaster.service;


import com.crudmaster.dto.state.StateDto;
import com.crudmaster.dto.state.StateFilterDto;
import com.crudmaster.dto.state.StateFilterReturnDto;
import com.crudmaster.entity.StateEntity;
import com.crudmaster.mapper.StateMapper;
import com.crudmaster.repository.StateRepo;
import com.crudmaster.specification.statespecification.StateSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService{

    private StateRepo stateRepo;
    private StateMapper stateMapper;

    public StateServiceImpl(StateRepo stateRepo, StateMapper stateMapper) {
        this.stateRepo = stateRepo;
        this.stateMapper=stateMapper;
    }

    @Override
    public StateDto saveAll(StateDto stateDto) {
         StateEntity states = stateRepo.save(stateMapper.stateDTOToState(stateDto));
         StateDto statesDto=stateMapper.stateToStateDTO(states);
        return statesDto;
    }

    @Override
    public List<StateDto> getAll(Integer pageNumber, Integer pageSize) {
        PageRequest p= PageRequest.of(pageNumber,pageSize);
        Page<StateEntity> page = stateRepo.findByDeletedAtNull(p);
        List<StateDto> content = page.stream()
                .map(entity -> stateMapper.stateToStateDTO(entity)) // or use MapStruct
                .collect(Collectors.toList());
        return content;
    }

    @Override
    public Boolean updateState(Long stateId,StateDto stateDto) {
        StateEntity stateEntity = stateRepo.findById(stateId).orElseThrow(
                ()-> new RuntimeException("state Id not found...")
        );
        stateEntity.setStateName(stateDto.getStateName());
        stateRepo.save(stateEntity);
        return true;
    }
    @Override
    public Boolean patchState(Long stateId,StateDto stateDto) {
        StateEntity stateEntity = stateRepo.findById(stateId).orElseThrow(
                ()-> new RuntimeException("state Id Not found...")
        );
        stateEntity.setStatus(stateDto.getStatus());
        stateRepo.save(stateEntity);
        return true;
    }

    @Override
    public String deleteState(Long stateId) {
        StateEntity stateEntity = stateRepo.findById(stateId).orElseThrow(
                ()->new RuntimeException("State Id Not found...")
        );
        stateRepo.delete(stateEntity);
        return "Deletion done sucessfully";
    }


    @Override
    public Page<StateFilterReturnDto> searchState(StateFilterDto filter) {
        Specification<StateEntity> spec= StateSpecification.buildSpecification(filter);
        Sort.Direction direction=filter.getSortDir().equalsIgnoreCase("asc") ? Sort.Direction.ASC:Sort.Direction.DESC;
        Pageable  pageable=PageRequest.of(filter.getPage(), filter.getSize(),Sort.by(direction, filter.getSortBy()));
          Page<StateFilterReturnDto> stateFilterReturnDto= stateRepo.findAll(spec,pageable).map(stateMapper:: stateToFilterReturnDto);
        return stateFilterReturnDto;
    }
}

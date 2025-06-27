package com.crudmaster.service;

import com.crudmaster.dto.export.CityExportDto;
import com.crudmaster.dto.export.PincodeExportDto;
import com.crudmaster.dto.export.StateExportDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.PincodeEntity;
import com.crudmaster.entity.StateEntity;
import com.crudmaster.mapper.ExportMapper;
import com.crudmaster.repository.CityRepo;
import com.crudmaster.repository.StateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExportServiceImpl implements ExportService{

    @Autowired
    private StateRepo stateRepo;
    @Autowired
    private ExportMapper exportMapper;
    @Autowired
    private CityRepo cityRepo;
    @Override
    @Transactional(readOnly = true)
    public List<StateExportDto> getAllData() {
        List<StateEntity> stateEntities = stateRepo.findAllStatesWithCities();
        List<CityEntity> cityEntities = cityRepo.findCitiesWithPincodes();

        Map<Long, List<CityEntity>> stateIdToCities = cityEntities.stream()
                .collect(Collectors.groupingBy(c -> c.getStateEntity().getStateId()));

        Map<Long, List<PincodeEntity>> cityIdToPincodes = cityEntities.stream()
                .flatMap(city -> city.getPincodeEntity().stream()
                        .map(p -> Map.entry(city.getCityId(), p)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        return stateEntities.stream().map(stateEntity -> {
            StateExportDto stateDTO = exportMapper.toStateDTO(stateEntity);
            List<CityExportDto> cityDTOs = stateIdToCities.getOrDefault(stateEntity.getStateId(), List.of()).stream()
                    .map(cityEntity -> {
                        CityExportDto cityDTO = exportMapper.toCityDTO(cityEntity);
                        List<PincodeExportDto> pinDtos = cityIdToPincodes.getOrDefault(cityEntity.getCityId(), List.of()).stream()
                                .map(exportMapper::toPincodeDTO).toList();
                        cityDTO.setPincodes(pinDtos);
                        return cityDTO;
                    }).toList();
            stateDTO.setCities(cityDTOs);
            return stateDTO;
        }).toList();
    }
}

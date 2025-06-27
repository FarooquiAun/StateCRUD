package com.crudmaster.service;

import com.crudmaster.dto.export.StateExportDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ExportService {
    List<StateExportDto> getAllData();
}

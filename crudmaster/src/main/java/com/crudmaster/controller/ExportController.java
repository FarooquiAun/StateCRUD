package com.crudmaster.controller;

import com.crudmaster.dto.export.CityExportDto;
import com.crudmaster.dto.export.StateExportDto;
import com.crudmaster.export.ExportUtils;
import com.crudmaster.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/export")
public class ExportController {
    @Autowired
    private ExportService exportService;
    @Autowired
    private ExportUtils exportUtils;

    @GetMapping("/excel/states")
    public ResponseEntity<Resource> exportStatesExcel() throws IOException{
        List<StateExportDto> data=exportService.getAllData();
        ByteArrayInputStream stream=exportUtils.exportStateLevel(data);
        return buildResponse(stream,"states_export_"+ LocalDate.now()+".xlsx");
    }
    @GetMapping("/excel/cities")
    public ResponseEntity<Resource> exportCityExcel() throws IOException{
        List<StateExportDto> data=exportService.getAllData();
        ByteArrayInputStream stream=exportUtils.exportCityLevel(data);
        return buildResponse(stream,"city_export_"+ LocalDate.now()+".xlsx");
    }
    @GetMapping("/excel/pincodes")
    public ResponseEntity<Resource> exportPincodeExcel() throws IOException{
        List<StateExportDto> data=exportService.getAllData();
        ByteArrayInputStream stream=exportUtils.exportPincodeLevel(data);
        return buildResponse(stream,"pincode_export_"+ LocalDate.now()+".xlsx");
    }

    private ResponseEntity<Resource> buildResponse(ByteArrayInputStream stream, String filename) {
        InputStreamResource file = new InputStreamResource(stream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
    }
}

package com.crudmaster.export;

import com.crudmaster.dto.export.CityExportDto;
import com.crudmaster.dto.export.PincodeExportDto;
import com.crudmaster.dto.export.StateExportDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExportUtils {
    public ByteArrayInputStream exportStateLevel(List<StateExportDto> data) throws IOException{
        try(Workbook workbook=new XSSFWorkbook(); ByteArrayOutputStream out=new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet();
            String headers[] = {"State Id", "State Name", "Status", "CreatedAt"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
            int rowIdx = 1;
            for (StateExportDto states : data) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(states.getId());
                row.createCell(1).setCellValue(states.getStateName());
                row.createCell(2).setCellValue(String.valueOf(states.getStatus()));
                row.createCell(3).setCellValue(states.getCreatedAt().toString());
            }
            for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayInputStream exportCityLevel(List<StateExportDto> data) throws IOException{
        try(Workbook workbook=new XSSFWorkbook();ByteArrayOutputStream out=new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet();
            String headers[]={"State Id","State Name","City Id","City Name","Status","Created At"};
            Row headerRow=sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
            int rowIdx=1;
            for (StateExportDto state:data){
                if (state.getCities()==null) continue;
                for (CityExportDto city : state.getCities()) {
                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(state.getId());
                    row.createCell(1).setCellValue(state.getStateName());
                    row.createCell(2).setCellValue(city.getId());
                    row.createCell(3).setCellValue(city.getCityName());
                    row.createCell(4).setCellValue(String.valueOf(city.getStatus()));
                    row.createCell(5).setCellValue(city.getCreatedAt().toString());
                }
            }
            for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayInputStream exportPincodeLevel(List<StateExportDto> data) throws  IOException{
        try(Workbook workbook=new XSSFWorkbook();ByteArrayOutputStream out=new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet();
            String headers[]={"State ID", "State Name", "City ID", "City Name",
                    "Pincode ID", "Pincode", "Status", "Created At"};
            Row headerRows=sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRows.createCell(i).setCellValue(headers[i]);
            }

            int rowIdx=1;
            for (StateExportDto states:data){
                if(states.getCities()==null) continue;
                for (CityExportDto cities:states.getCities()){
                    if (cities.getPincodes()==null) continue;
                        for(PincodeExportDto pincodes:cities.getPincodes()){
                                Row row=sheet.createRow(rowIdx++);
                            row.createCell(0).setCellValue(states.getId());
                            row.createCell(1).setCellValue(states.getStateName());
                            row.createCell(2).setCellValue(cities.getId());
                            row.createCell(3).setCellValue(cities.getCityName());
                            row.createCell(4).setCellValue(pincodes.getId());
                            row.createCell(5).setCellValue(pincodes.getPincode());
                            row.createCell(6).setCellValue(String.valueOf(pincodes.getStatus()));
                            row.createCell(7).setCellValue(pincodes.getCreatedAt().toString());
                        }
                }
            }
            for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}

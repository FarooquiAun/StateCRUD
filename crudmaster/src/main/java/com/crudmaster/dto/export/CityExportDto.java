package com.crudmaster.dto.export;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CityExportDto {
    private long id;
    private String cityName;
    private char status;
    private LocalDateTime createdAt;
    private List<PincodeExportDto> pincodes=new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<PincodeExportDto> getPincodes() {
        return pincodes;
    }

    public void setPincodes(List<PincodeExportDto> pincodes) {
        this.pincodes = pincodes;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}

package com.crudmaster.dto.export;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StateExportDto {
    private Long id;
    private String stateName;
    private Character status;
    private LocalDateTime createdAt;
    private List<CityExportDto> cities=new ArrayList<>();

    public List<CityExportDto> getCities() {
        return cities;
    }

    public void setCities(List<CityExportDto> cities) {
        this.cities = cities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }
}

package com.crudmaster.dto.city;

import com.crudmaster.entity.StateEntity;

public class CityDto {
    private String cityName;
    private Character status;
   private StateEntity stateEntity;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public StateEntity getStateEntity() {
        return stateEntity;
    }

    public void setStateEntity(StateEntity stateEntity) {
        this.stateEntity = stateEntity;
    }

}

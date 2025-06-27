package com.crudmaster.dto.pincode;

import com.crudmaster.entity.CityEntity;

public class PincodeDto {
    private Long pincode;
    private Character status;
    private CityEntity city;


    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }
}

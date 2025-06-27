package com.crudmaster.dto.pincode;

import com.crudmaster.entity.CityEntity;

public class PincodeFilterReturnDto {
    private Long pincode;
    private Character status;
    private CityEntity city;

    public PincodeFilterReturnDto(Long pincode, CityEntity city, Character status) {
        this.pincode = pincode;
        this.city = city;
        this.status = status;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

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
}

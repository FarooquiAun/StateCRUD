package com.crudmaster.dto.state;

import java.time.LocalDateTime;

public class StateFilterReturnDto {
    private Long stateId;
    private String StateName;
    private LocalDateTime createdAt;
    private Character status;

    public StateFilterReturnDto(Long stateId, String stateName, LocalDateTime createdAt, Character status) {
        this.stateId = stateId;
        StateName = stateName;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }
}

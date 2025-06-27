package com.crudmaster.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "state")
@SQLDelete(sql="UPDATE state SET status='N',deleted_at=now() where state_id=?")
public class StateEntity {
    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long stateId;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name="status")
    private char status;

    @OneToMany(mappedBy = "stateEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CityEntity> cityEntity;

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public List<CityEntity> getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(List<CityEntity> cityEntity) {
        this.cityEntity = cityEntity;
    }
}

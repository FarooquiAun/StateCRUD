package com.crudmaster.repository;

import com.crudmaster.dto.pincode.PincodeDto;
import com.crudmaster.entity.PincodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PincodeRepo extends JpaRepository<PincodeEntity,Long>, JpaSpecificationExecutor<PincodeEntity> {
    Page<PincodeEntity> findByDeletedAtNull(PageRequest p);
}

package com.crudmaster.repository;

import com.crudmaster.dto.city.CityDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.StateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepo extends JpaRepository<CityEntity,Long>, JpaSpecificationExecutor<CityEntity> {

    Page<CityEntity> findByDeletedAtNull(PageRequest p);

    @Query("SELECT DISTINCT c FROM CityEntity c LEFT JOIN FETCH c.pincodeEntity WHERE c.stateEntity.stateId IN (SELECT s.stateId FROM StateEntity s WHERE s.status = 'Y')")
    List<CityEntity> findCitiesWithPincodes();

}

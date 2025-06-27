package com.crudmaster.repository;

import com.crudmaster.dto.state.StateDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.StateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.nimbus.State;
import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepo extends JpaRepository<StateEntity,Long>, JpaSpecificationExecutor<StateEntity> {
    Page<StateEntity> findByDeletedAtNull(PageRequest p);

    @Query("SELECT DISTINCT s FROM StateEntity s LEFT JOIN FETCH s.cityEntity WHERE s.status = 'Y'")
    List<StateEntity> findAllStatesWithCities();

    Optional<StateEntity> findByStateNameIgnoreCase(String stateName);
}

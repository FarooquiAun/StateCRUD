package com.crudmaster.specification.pincodespecification;

import com.crudmaster.dto.pincode.PincodeFilterDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.PincodeEntity;
import com.crudmaster.entity.StateEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PincodeSpecification {
    public static Specification<PincodeEntity> createdfrom(LocalDate from){
        return (root, query, cb) ->
        from==null? null: cb.greaterThanOrEqualTo(root.get("createdAt"),from);
    }
    public static Specification<PincodeEntity> createdTo(LocalDate to){
        return (root, query, cb) ->
        to==null? null : cb.lessThanOrEqualTo(root.get("createdAt"),to);
    }
    public static Specification<PincodeEntity> hasPincode(Long pincode){
        return ((root, query, cb) ->
                pincode==null ?null: cb.equal(root.get("pincode"),pincode)
                );
    }
    public static Specification<PincodeEntity> hasCity(String cityName){
        return (root, query, cb) ->{
           if(cityName==null || cityName.isBlank()) return null;
           Join<PincodeEntity,CityEntity> cityJoin=root.join("cityEntity", JoinType.INNER);
           return cb.like(cb.lower(cityJoin.get("cityName")),"%"+cityName.toLowerCase()+"%");
        };
    }
    public static Specification<PincodeEntity> hasState(String stateName){
        return ((root, query, cb)-> {
            if (stateName == null || stateName.isBlank()) return null;
            Join<PincodeEntity, CityEntity> cityjoin = root.join("cityEntity",JoinType.INNER);
            Join<PincodeEntity, StateEntity> statejoin=cityjoin.join("stateEntity",JoinType.INNER);
            return cb.like(cb.lower(statejoin.get("stateName")),"%"+stateName.toLowerCase()+"%");
        }
                );
    }
    public static Specification<PincodeEntity> isNotDeleted(){
        return (root, query, cb) ->
            cb.like(root.get("status"),"Y");
    }

    public static Specification<PincodeEntity> buildSpecification(PincodeFilterDto filter) {
        Specification<PincodeEntity> spec = (root, query, cb) -> cb.conjunction();
        spec = spec.and(isNotDeleted());
        if (filter.getCreatedFrom() != null) {
            spec = spec.and(createdfrom(filter.getCreatedFrom()));
        }
        if (filter.getCreatedTo() != null) {
            spec = spec.and(createdTo(filter.getCreatedTo()));
        }
        if (filter.getPincode() != null) {
            spec = spec.and(hasPincode(filter.getPincode()));
        }
        if (filter.getCityName() != null && !filter.getCityName().isBlank()) {
            spec = spec.and(hasCity(filter.getCityName()));
        }
        if (filter.getStateName() != null && !filter.getStateName().isBlank()) {
            spec = spec.and(hasState(filter.getStateName()));
        }
        return spec;
    }

}

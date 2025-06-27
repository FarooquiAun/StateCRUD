 package com.crudmaster.specification.statespecification;

import com.crudmaster.dto.state.StateFilterDto;
import com.crudmaster.entity.StateEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

 public class StateSpecification {

    public static Specification<StateEntity> createdFrom(LocalDate from){
        return ((root, query, cb) -> from ==null ? null:
                cb.greaterThanOrEqualTo(root.get("createdAt"),from)
                );
    }
    public static Specification<StateEntity> createdTo(LocalDate to){
        return ((root, query, cb) ->to ==null ?
                null: cb.lessThanOrEqualTo(root.get("createdAt"),to)
        );
    }
    public static Specification<StateEntity>  hasStateName(String stateName){
        return ((root, query, cb) ->(stateName==null || stateName.isBlank())?null:
                  cb.like((cb.lower(root.get("stateName"))),"%" + stateName.toLowerCase()+"%")
                );
    }
    public static Specification<StateEntity> isNotDeleted(){
        return ((root, query, cb) -> cb.equal(root.get("status"),"Y")
        );
    }

    public static Specification<StateEntity> buildSpecification(StateFilterDto filter){
        Specification<StateEntity> spec = (root, query, cb) -> cb.conjunction();

        spec = spec.and(isNotDeleted());
        if (filter.getCreatedFrom() != null) {
            spec = spec.and(createdFrom(filter.getCreatedFrom()));
        }
        if (filter.getCreatedTo() != null) {
            spec = spec.and(createdTo(filter.getCreatedTo()));
        }
        if (filter.getStateName() != null && !filter.getStateName().isBlank()) {
            spec = spec.and(hasStateName(filter.getStateName()));
        }
           return spec;
    }
}

package com.crudmaster.specification.cityspecification;

import com.crudmaster.dto.city.CityFilterDto;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.entity.StateEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CitySpecification {
    public static Specification<CityEntity> createdFrom(LocalDate from){
        return ((root, query, cb) ->
                 from==null ? null :cb.greaterThanOrEqualTo(root.get("createdAt"),from)
                );
    }
    public static Specification<CityEntity> createdTo(LocalDate to){
        return ((root, query, cb) ->
                to==null ? null :cb.greaterThanOrEqualTo(root.get("createdAt"),to)
        );
    }
    public  static Specification<CityEntity> hasCityName(String cityName){
        return ((root, query, cb) -> (cityName==null || cityName.isBlank())?
                  null:cb.like(cb.lower(root.get("cityName")),"%"+cityName.toLowerCase()+"%")
                );
    }
    public static Specification<CityEntity>  hasStateName(String stateName){
        return ((root, query, cb) ->
        {
            if(stateName==null || stateName.isBlank()) return null;
            Join<CityEntity, StateEntity> stateJoin=root.join("stateEntity", JoinType.INNER);
            return cb.like(cb.lower(stateJoin.get("stateName")),"%"+stateName.toLowerCase()+"%");
        });
    }
    public static Specification<CityEntity> isNotDeleted(){
        return ((root, query, cb) ->cb.equal(root.get("status"),"Y")
        );
    }

    public static Specification<CityEntity> buildSpecification(CityFilterDto filterDto){
        Specification<CityEntity> spec=(root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
             spec=spec.and(isNotDeleted());
             if(filterDto.getCreatedFrom()!=null){
                 spec=spec.and(createdFrom(filterDto.getCreatedFrom()));
             }
             if (filterDto.getCreatedTo()!=null){
                 spec=spec.and(createdTo(filterDto.getCreatedTo()));
             }
             if(filterDto.getCityName()!=null && !filterDto.getCityName().isBlank()){
                    spec=spec.and(hasCityName(filterDto.getCityName()));
             }
             if (filterDto.getStateName()!=null && !filterDto.getStateName().isBlank()){
                 spec=spec.and(hasStateName((filterDto.getStateName())));
             }


        return spec;
    }

}

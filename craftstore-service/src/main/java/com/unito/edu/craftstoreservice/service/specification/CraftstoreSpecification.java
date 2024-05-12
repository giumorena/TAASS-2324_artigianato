package com.unito.edu.craftstoreservice.service.specification;

import com.unito.edu.craftstoreservice.model.Address;
import com.unito.edu.craftstoreservice.model.Craftstore;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class CraftstoreSpecification {

    // Filter to take craftstores by name (like a specific string)
    public static Specification<Craftstore> hasNameLike(String name){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"),"%" + name + "%");
    }

    // Filter to take craftstores by category
    public static Specification<Craftstore> belongsToCategory(String category){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category"),category);
    }

    // Filter to take craftstores by region
    // The distinct clause is for not considering the same craftstore multiple times
    // if it has multiple addresses that match the criteria
    public static Specification<Craftstore> isInRegion(String region){
        return (root, query, criteriaBuilder) ->{
            query.distinct(true);
            Join<Craftstore, Address> addressJoin =root.join("addressList",JoinType.INNER);
            return criteriaBuilder.equal(addressJoin.get("region"),region);
        };
    }

    // Filter to take craftstores by province
    // The distinct clause is for not considering the same craftstore multiple times
    // if it has multiple addresses that match the criteria
    public static Specification<Craftstore> isInProvince(String province){
        return (root, query, criteriaBuilder) ->{
            query.distinct(true);
            Join<Craftstore, Address> addressJoin =root.join("addressList",JoinType.INNER);
            return criteriaBuilder.equal(addressJoin.get("province"),province);
        };
    }

    // Filter to take craftstores by city
    // The distinct clause is for not considering the same craftstore multiple times
    // if it has multiple addresses that match the criteria
    public static Specification<Craftstore> isInCity(String city){
        return (root, query, criteriaBuilder) ->{
            query.distinct(true);
            Join<Craftstore, Address> addressJoin =root.join("addressList",JoinType.INNER);
            return criteriaBuilder.equal(addressJoin.get("city"),city);
        };
    }
}

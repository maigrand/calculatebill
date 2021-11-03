package com.maigrand.calculatebill.repository;

import com.maigrand.calculatebill.entity.PositionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends MongoRepository<PositionEntity, String> {

    boolean existsByName(String name);
}

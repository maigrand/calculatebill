package com.maigrand.calculatebill.repository;

import com.maigrand.calculatebill.entity.PositionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends MongoRepository<PositionEntity, String> {

    boolean existsByName(String name);

    Optional<PositionEntity> findByName(String name);
}

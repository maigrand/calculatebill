package com.maigrand.calculatebill.repository;

import com.maigrand.calculatebill.entity.GuestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends MongoRepository<GuestEntity, String> {

    boolean existsByName(String name);
}

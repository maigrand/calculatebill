package com.maigrand.calculatebill.repository;

import com.maigrand.calculatebill.entity.BillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends MongoRepository<BillEntity, String> {
}

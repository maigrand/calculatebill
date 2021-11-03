package com.maigrand.calculatebill.repository;

import com.maigrand.calculatebill.entity.BillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends MongoRepository<BillEntity, String> {

    List<BillEntity> findAllByUserId(String id);
}

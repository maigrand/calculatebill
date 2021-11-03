package com.maigrand.calculatebill.repository;

import com.maigrand.calculatebill.entity.BillGuestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillGuestRepository extends MongoRepository<BillGuestEntity, String> {

    Optional<BillGuestEntity> findByBillIdAndGuestId(String billId, String guestId);
}

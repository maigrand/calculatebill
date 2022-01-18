package com.maigrand.calculatebill.repository.bill;

import com.maigrand.calculatebill.entity.bill.BillEntity;
import com.maigrand.calculatebill.entity.bill.GuestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Integer> {

    Page<GuestEntity> findAllByBillEntity(BillEntity billEntity, Pageable pageable);
}

package com.maigrand.calculatebill.repository.bill;

import com.maigrand.calculatebill.entity.bill.BillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Integer> {

    Page<BillEntity> findAllByUserId(Integer userId, Pageable pageable);

    Optional<BillEntity> findByIdAndUserId(Integer billId, Integer userId);
}

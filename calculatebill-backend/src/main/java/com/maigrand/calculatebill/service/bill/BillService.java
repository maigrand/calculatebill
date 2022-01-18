package com.maigrand.calculatebill.service.bill;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.entity.bill.BillEntity;
import com.maigrand.calculatebill.payload.PaginationDetails;
import com.maigrand.calculatebill.payload.bill.BillDetails;
import com.maigrand.calculatebill.repository.bill.BillRepository;
import com.maigrand.calculatebill.validator.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;

    public PaginationDetails<BillEntity> findAll(UserEntity userEntity, Pageable pageable) {
        return new PaginationDetails<>(billRepository.findAllByUserId(userEntity.getId(), pageable));
    }

    public BillEntity findById(Integer id, UserEntity userEntity) {
        return billRepository.findByIdAndUserId(id, userEntity.getId())
                .orElseThrow(() -> new EntityNotFoundException("bill not found"));
    }

    @Validated(OnCreate.class)
    public BillEntity create(UserEntity userEntity, @Valid BillDetails details) {
        BillEntity entity = new BillEntity();
        entity.setName(details.getName());
        entity.setTipsPercent(Optional.of(details.getTipsPercent()).orElse(0));
        entity.setUserId(userEntity.getId());

        return billRepository.save(entity);
    }

    public BillEntity updateTotalCost(BillEntity billEntity, Float guestTotalCost) {
        billEntity.setTotalCost(billEntity.getTotalCost() + guestTotalCost);
        return billRepository.save(billEntity);
    }
}

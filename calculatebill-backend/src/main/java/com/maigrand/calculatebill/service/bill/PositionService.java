package com.maigrand.calculatebill.service.bill;

import com.maigrand.calculatebill.entity.bill.BillEntity;
import com.maigrand.calculatebill.entity.bill.PositionEntity;
import com.maigrand.calculatebill.payload.PaginationDetails;
import com.maigrand.calculatebill.payload.bill.PositionDetails;
import com.maigrand.calculatebill.repository.bill.PositionRepository;
import com.maigrand.calculatebill.validator.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    public PaginationDetails<PositionEntity> findAll(BillEntity billEntity, Pageable pageable) {
        return new PaginationDetails<>(positionRepository.findAllByBillEntity(billEntity, pageable));
    }

    @Validated(OnCreate.class)
    public PositionEntity create(BillEntity billEntity, @Valid PositionDetails details) {
        PositionEntity entity = new PositionEntity();
        entity.setName(details.getName());
        entity.setCost(details.getCost());
        entity.setBillEntity(billEntity);

        return positionRepository.save(entity);
    }

    public PositionEntity findById(Integer positionId) {
        return positionRepository.findById(positionId)
                .orElseThrow(() -> new EntityNotFoundException("position not found"));
    }
}

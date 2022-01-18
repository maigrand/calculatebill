package com.maigrand.calculatebill.service.bill;

import com.maigrand.calculatebill.entity.bill.BillEntity;
import com.maigrand.calculatebill.entity.bill.GuestEntity;
import com.maigrand.calculatebill.payload.PaginationDetails;
import com.maigrand.calculatebill.payload.bill.GuestDetails;
import com.maigrand.calculatebill.repository.bill.GuestRepository;
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
public class GuestService {

    private final GuestRepository guestRepository;

    public PaginationDetails<GuestEntity> findAll(BillEntity billEntity, Pageable pageable) {
        return new PaginationDetails<>(guestRepository.findAllByBillEntity(billEntity, pageable));
    }

    @Validated(OnCreate.class)
    public GuestEntity create(BillEntity billEntity, @Valid GuestDetails details) {
        GuestEntity entity = new GuestEntity();
        entity.setName(details.getName());
        entity.setBillEntity(billEntity);

        return guestRepository.save(entity);
    }

    public GuestEntity findById(Integer guestId) {
        return guestRepository.findById(guestId)
                .orElseThrow(() -> new EntityNotFoundException("guest not found"));
    }
}

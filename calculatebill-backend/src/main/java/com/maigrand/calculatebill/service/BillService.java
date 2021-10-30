package com.maigrand.calculatebill.service;

import com.maigrand.calculatebill.entity.*;
import com.maigrand.calculatebill.exception.EntityNotFoundException;
import com.maigrand.calculatebill.payload.*;
import com.maigrand.calculatebill.repository.BillRepository;
import com.maigrand.calculatebill.validator.group.OnCreate;
import com.maigrand.calculatebill.validator.group.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

@Service
@Validated
@RequiredArgsConstructor
public class BillService {

    private final GuestService guestService;

    private final PositionService positionService;

    private final BillRepository billRepository;

    public BillEntity findById(String id) {
        return this.billRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("bill not found"));
    }

    public List<BillEntity> findAll() {
        return this.billRepository.findAll();
    }

    @Validated(OnCreate.class)
    public BillEntity create(@Valid BillDetails details) {
        BillEntity entity = new BillEntity();

        entity.setName(details.getName());
        Optional.ofNullable(details.getTips()).ifPresent(entity::setTips);

        return this.billRepository.save(entity);
    }

    @Validated(OnUpdate.class)
    public BillEntity update(String id, @Valid BillDetails details) {
        BillEntity entity = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(entity::setName);
        Optional.ofNullable(details.getTips()).ifPresent(entity::setTips);
        return this.billRepository.save(entity);
    }

    public BillEntity addGuest(String id, @Valid GuestDetails details) {
        BillEntity billEntity = findById(id);
        GuestEntity guestEntity = this.guestService.findByNameOrCreate(details);
        billEntity.addGuest(guestEntity);
        return this.billRepository.save(billEntity);
    }

    public BillEntity addGuestPosition(String id, String guestId, @Valid PositionDetails details) {
        BillEntity billEntity = findById(id);

        PositionEntity positionEntity = this.positionService.findByNameOrCreate(details);

        GuestEntity guestEntity = this.guestService.findById(guestId);
        guestEntity.addPosition(positionEntity);
        guestEntity = this.guestService.save(guestEntity);

        billEntity.removeGuest(guestEntity);
        billEntity.addGuest(guestEntity);
        billEntity = this.billRepository.save(billEntity);

        return billEntity;
    }

    public BillEntity removeGuest(String id, String guestId) {
        BillEntity billEntity = findById(id);
        GuestEntity guestEntity = this.guestService.findById(guestId);
        billEntity.removeGuest(guestEntity);
        return this.billRepository.save(billEntity);
    }

    public BillEntity calculate(String id) {
        BillEntity billEntity = findById(id);
        Set<BillMemberPojo> billMemberPojoList = new HashSet<>();

        Float totalCost = billEntity.getGuests().stream()
                .flatMap(guestEntity -> guestEntity.getPositions().stream()
                        .map(PositionEntity::getCost)).reduce(Float::sum).get();

        totalCost = totalCost + (totalCost * billEntity.getTips() / 100);

        for (GuestEntity guest : billEntity.getGuests()) {
            Float cost = guest.getPositions().stream()
                    .map(PositionEntity::getCost)
                    .reduce(Float::sum).get();

            guest.setPositions(new ArrayList<>());
            this.guestService.save(guest);

            BillMemberPojo billMemberPojo = new BillMemberPojo();
            billMemberPojo.setMemberName(guest.getName());
            billMemberPojo.setCost(cost + (cost * billEntity.getTips() / 100));
            billMemberPojoList.add(billMemberPojo);
        }

        billEntity.setBillMemberPojoSet(billMemberPojoList);
        billEntity.setTotalCost(totalCost);
        billEntity.setGuests(new HashSet<>());
        this.billRepository.save(billEntity);

        return billEntity;
    }
}

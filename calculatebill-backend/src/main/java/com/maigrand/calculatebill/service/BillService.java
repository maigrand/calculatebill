package com.maigrand.calculatebill.service;

import com.maigrand.calculatebill.entity.*;
import com.maigrand.calculatebill.exception.EntityNotFoundException;
import com.maigrand.calculatebill.payload.BillDetails;
import com.maigrand.calculatebill.payload.BillGuestDetails;
import com.maigrand.calculatebill.repository.BillGuestRepository;
import com.maigrand.calculatebill.repository.BillRepository;
import com.maigrand.calculatebill.validator.group.OnCreate;
import com.maigrand.calculatebill.validator.group.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class BillService {

    private final GuestService guestService;

    private final PositionService positionService;

    private final BillRepository billRepository;

    private final BillGuestRepository billGuestRepository;

    public List<BillEntity> findAll() {
        return this.billRepository.findAll();
    }

    public List<BillEntity> findAll(UserEntity userEntity) {
        return this.billRepository.findAllByUserId(userEntity.getId());
    }

    public BillEntity findById(String id) {
        return this.billRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("bill not found"));
    }

    @Validated(OnCreate.class)
    public BillEntity create(UserEntity userEntity, @Valid BillDetails details) {
        BillEntity entity = new BillEntity();
        entity.setUserId(userEntity.getId());
        entity.setName(details.getName());
        Optional.ofNullable(details.getTips()).ifPresent(entity::setTips);

        return this.billRepository.save(entity);
    }

    //TODO: todo
    @Validated(OnUpdate.class)
    public BillEntity update(String id, @Valid BillDetails details) {
        BillEntity entity = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(entity::setName);
        Optional.ofNullable(details.getTips()).ifPresent(entity::setTips);
        return this.billRepository.save(entity);
    }

    @Validated(OnCreate.class)
    public BillEntity addGuest(String id, @Valid BillGuestDetails details) {
        BillEntity billEntity = findById(id);
        GuestEntity guestEntity = this.guestService.findById(details.getGuestId());
        PositionEntity positionEntity = this.positionService.findById(details.getPositionId());

        Optional<BillGuestEntity> optionalBillGuest = this.billGuestRepository
                .findByBillIdAndGuestId(billEntity.getId(), guestEntity.getId());
        if (optionalBillGuest.isPresent()) {
            BillGuestEntity billGuest = optionalBillGuest.get();
            billGuest.addPosition(positionEntity);
            billGuest = this.billGuestRepository.save(billGuest);
            billEntity.addGuest(billGuest);
            return calculate(billEntity);
        }

        BillGuestEntity billGuest = new BillGuestEntity();
        billGuest.setBill(billEntity);
        billGuest.setGuest(guestEntity);
        billGuest.setPositions(List.of(positionEntity));
        billGuest = this.billGuestRepository.save(billGuest);
        billEntity.addGuest(billGuest);
        return calculate(billEntity);
    }

    private BillEntity calculate(BillEntity billEntity) {
        Float totalCostWithoutTips = billEntity.getGuests().stream()
                .flatMap(billGuestEntity -> billGuestEntity.getPositions().stream()
                        .map(PositionEntity::getCost)).reduce(Float::sum).get();

        Float totalCost = totalCostWithoutTips + (totalCostWithoutTips * billEntity.getTips() / 100);

        for (BillGuestEntity guest : billEntity.getGuests()) {
            Float totalGuestCostWithoutTips = guest.getPositions().stream()
                    .map(PositionEntity::getCost)
                    .reduce(Float::sum).get();

            Float totalGuestCost = totalGuestCostWithoutTips + (totalGuestCostWithoutTips * billEntity.getTips() / 100);
            guest.setTotalCost(totalGuestCost);
            billEntity.addGuest(this.billGuestRepository.save(guest));
        }

        billEntity.setTotalCost(totalCost);
        return this.billRepository.save(billEntity);
    }
}

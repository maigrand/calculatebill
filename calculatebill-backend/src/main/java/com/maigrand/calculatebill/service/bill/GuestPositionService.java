package com.maigrand.calculatebill.service.bill;

import com.maigrand.calculatebill.entity.bill.*;
import com.maigrand.calculatebill.payload.bill.*;
import com.maigrand.calculatebill.repository.bill.GuestPositionRepository;
import com.maigrand.calculatebill.validator.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class GuestPositionService {

    private final BillService billService;

    private final PositionService positionService;

    private final GuestPositionRepository guestPositionRepository;

    public GuestPositionDto findAllByGuestEntity(BillEntity billEntity, GuestEntity guestEntity) {
        List<GuestPositionEntity> byGuestEntity = guestPositionRepository.findAllByGuestEntity(guestEntity);
        return entityToDto(billEntity, guestEntity, byGuestEntity);
    }

    @Validated(OnCreate.class)
    public GuestPositionDto create(
            BillEntity billEntity,
            GuestEntity guestEntity,
            @Valid GuestPositionDetails details) {
        GuestPositionEntity entity = new GuestPositionEntity();

        PositionEntity positionEntity = positionService.findById(details.getPositionId());

        Optional<GuestPositionEntity> optional =
                guestPositionRepository.findByGuestEntityAndPositionEntity(guestEntity, positionEntity);
        if (optional.isPresent()) {
            entity = optional.get();
        }

        entity.setAmount(details.getAmount());
        entity.setGuestEntity(guestEntity);
        entity.setPositionEntity(positionEntity);

        GuestPositionEntity save = guestPositionRepository.save(entity);
        GuestPositionDto dto = entityToDto(billEntity, guestEntity, List.of(save));
        billService.updateTotalCost(billEntity, dto.getTotalCost());

        GuestPositionDto allByGuestEntity = findAllByGuestEntity(billEntity, guestEntity);
        return allByGuestEntity;
    }

    private GuestPositionDto entityToDto(
            BillEntity billEntity,
            GuestEntity guestEntity,
            List<GuestPositionEntity> entityList) {
        GuestPositionDto dto = new GuestPositionDto();
        Float totalCost = 0f;
        dto.setGuestId(guestEntity.getId());
        dto.setGuestName(guestEntity.getName());
        for (GuestPositionEntity entity : entityList) {
            PositionEntity positionEntity = entity.getPositionEntity();
            GuestPositionsDto positionsDto = new GuestPositionsDto();
            positionsDto.setPositionId(positionEntity.getId());
            positionsDto.setPositionName(positionEntity.getName());
            positionsDto.setPositionCost(positionEntity.getCost());
            positionsDto.setAmount(entity.getAmount());
            dto.addPosition(positionsDto);
            totalCost += positionEntity.getCost() * entity.getAmount();
        }

        float tipsCost = billEntity.getTipsPercent() * totalCost / 100;

        dto.setTotalCost(totalCost);
        dto.setTipsCost(tipsCost);
        dto.setTotalCostWithTips(tipsCost + totalCost);
        return dto;
    }
}

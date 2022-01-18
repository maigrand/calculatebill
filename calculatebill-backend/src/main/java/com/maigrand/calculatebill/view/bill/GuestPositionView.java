package com.maigrand.calculatebill.view.bill;

import com.maigrand.calculatebill.payload.bill.GuestPositionDto;
import com.maigrand.calculatebill.payload.bill.GuestPositionsDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GuestPositionView {

    private final Integer guestId;

    private final String guestName;

    private final Float totalCost;

    private final Float tipsCost;

    private final Float totalCostWithTips;

    private final List<GuestPositionsView> positions = new ArrayList<>();

    public GuestPositionView(GuestPositionDto dto) {
        this.guestId = dto.getGuestId();
        this.guestName = dto.getGuestName();
        this.totalCost = dto.getTotalCost();
        this.tipsCost = dto.getTipsCost();
        this.totalCostWithTips = dto.getTotalCostWithTips();
        for (GuestPositionsDto position : dto.getPositions()) {
            positions.add(new GuestPositionsView(position));
        }
    }
}

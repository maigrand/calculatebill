package com.maigrand.calculatebill.view.bill;

import com.maigrand.calculatebill.payload.bill.GuestPositionsDto;
import lombok.Getter;

@Getter
public class GuestPositionsView {

    private final Integer id;

    private final String name;

    private final Float cost;

    private final Float amount;

    public GuestPositionsView(GuestPositionsDto dto) {
        this.id = dto.getPositionId();
        this.name = dto.getPositionName();
        this.cost = dto.getPositionCost();
        this.amount = dto.getAmount();
    }
}

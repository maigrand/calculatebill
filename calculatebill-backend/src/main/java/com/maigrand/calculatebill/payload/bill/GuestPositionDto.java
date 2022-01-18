package com.maigrand.calculatebill.payload.bill;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GuestPositionDto {

    private Integer guestId;

    private String guestName;

    private Float totalCost;

    private Float tipsCost;

    private Float totalCostWithTips;

    private final List<GuestPositionsDto> positions = new ArrayList<>();

    public void addPosition(GuestPositionsDto dto) {
        this.positions.add(dto);
    }
}

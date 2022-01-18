package com.maigrand.calculatebill.payload.bill;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestPositionsDto {

    private Integer positionId;

    private String positionName;

    private Float positionCost;

    private Float amount;
}

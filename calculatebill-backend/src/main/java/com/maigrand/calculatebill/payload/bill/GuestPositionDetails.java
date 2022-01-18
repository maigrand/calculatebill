package com.maigrand.calculatebill.payload.bill;

import com.maigrand.calculatebill.validator.OnCreate;
import com.maigrand.calculatebill.validator.OnUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class GuestPositionDetails {

    @NotNull(message = "{positionId.not_null}", groups = {OnCreate.class})
    private Integer positionId;

    @NotNull(message = "{amount.not_null}", groups = {OnCreate.class, OnUpdate.class})
    private Float amount;
}

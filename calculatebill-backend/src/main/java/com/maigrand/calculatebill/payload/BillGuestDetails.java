package com.maigrand.calculatebill.payload;

import com.maigrand.calculatebill.validator.group.OnCreate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class BillGuestDetails {

    @NotNull(message = "{guestId.not_null}", groups = OnCreate.class)
    private String guestId;

    @NotNull(message = "{positionId.not_null}", groups = OnCreate.class)
    private String positionId;
}

package com.maigrand.calculatebill.payload.bill;

import com.maigrand.calculatebill.validator.OnCreate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class GuestDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;
}

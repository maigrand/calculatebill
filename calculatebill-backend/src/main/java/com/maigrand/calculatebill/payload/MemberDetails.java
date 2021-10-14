package com.maigrand.calculatebill.payload;

import com.maigrand.calculatebill.validator.group.OnCreate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class MemberDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;
}

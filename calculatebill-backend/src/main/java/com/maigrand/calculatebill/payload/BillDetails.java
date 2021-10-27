package com.maigrand.calculatebill.payload;

import com.maigrand.calculatebill.validator.group.OnCreate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Data
public class BillDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;

    private Integer tips;

    private Set<String> memberNames;
}

package com.maigrand.calculatebill.payload;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class BillMemberPojo {

    private String memberName;

    private Float cost;

    @EqualsAndHashCode.Exclude
    private Set<String> positionNames;
}

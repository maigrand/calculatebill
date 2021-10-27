package com.maigrand.calculatebill.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.Date;
import java.util.Set;

@Document(collection = "bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String name;

    @Field(value = "date")
    private Date date;

    private Integer tips;

    @Field(value = "total_cost")
    private Float totalCost;

    @DBRef
    private Set<MemberEntity> members;
}

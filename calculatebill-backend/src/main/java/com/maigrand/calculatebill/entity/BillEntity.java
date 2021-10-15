package com.maigrand.calculatebill.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "memberPositionRelation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @DBRef
    private MemberEntity member;

    @DBRef
    private PositionEntity position;
}

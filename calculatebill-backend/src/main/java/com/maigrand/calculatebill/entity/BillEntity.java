package com.maigrand.calculatebill.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

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
    @Setter(AccessLevel.NONE)
    private Date date = new Date();

    private Integer tips;

    @Field(value = "total_cost")
    private Float totalCost;

    @DBRef
    private Set<MemberEntity> members = new HashSet<>();

    @DBRef
    private Set<PositionEntity> positions = new HashSet<>();

    public void addMember(MemberEntity memberEntity) {
        this.members.add(memberEntity);
    }

    public void addPosition(PositionEntity positionEntity) {
        this.positions.add(positionEntity);
    }

    public void removeMember(MemberEntity memberEntity) {
        this.members.remove(memberEntity);
    }
}

package com.maigrand.calculatebill.entity;

import com.maigrand.calculatebill.payload.BillMemberPojo;
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

    private Set<BillMemberPojo> billMemberPojoSet = new HashSet<>();

    @DBRef
    private Set<MemberEntity> members = new HashSet<>();

    public void addMember(MemberEntity memberEntity) {
        this.members.add(memberEntity);
    }

    public void removeMember(MemberEntity memberEntity) {
        this.members.remove(memberEntity);
    }

    public void addBillMemberPojo(BillMemberPojo billMemberPojo) {
        this.billMemberPojoSet.add(billMemberPojo);
    }
}

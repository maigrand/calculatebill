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
    private Set<GuestEntity> guests = new HashSet<>();

    public void addGuest(GuestEntity guestEntity) {
        this.guests.add(guestEntity);
    }

    public void removeGuest(GuestEntity guestEntity) {
        this.guests.remove(guestEntity);
    }

    public void addBillMemberPojo(BillMemberPojo billMemberPojo) {
        this.billMemberPojoSet.add(billMemberPojo);
    }
}

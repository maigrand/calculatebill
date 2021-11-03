package com.maigrand.calculatebill.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Document(collection = "bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BillEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String name;

    @JsonIgnore
    private String userId;

    @Field(value = "date")
    @Setter(AccessLevel.NONE)
    private Date date = new Date();

    @EqualsAndHashCode.Exclude
    private Integer tips = 1;

    @Field(value = "total_cost")
    @EqualsAndHashCode.Exclude
    private Float totalCost;

    @DBRef
    @EqualsAndHashCode.Exclude
    private Set<BillGuestEntity> guests = new HashSet<>();

    public void addGuest(BillGuestEntity billGuestEntity) {
        if (this.guests.contains(billGuestEntity)) {
            this.guests.remove(billGuestEntity);
            this.guests.add(billGuestEntity);
            return;
        }
        this.guests.add(billGuestEntity);
    }
}

package com.maigrand.calculatebill.view.bill;

import com.maigrand.calculatebill.entity.bill.GuestEntity;
import lombok.Getter;

@Getter
public class GuestView {

    private final Integer id;

    private final String name;

    public GuestView(GuestEntity guestEntity) {
        this.id = guestEntity.getId();
        this.name = guestEntity.getName();
    }
}

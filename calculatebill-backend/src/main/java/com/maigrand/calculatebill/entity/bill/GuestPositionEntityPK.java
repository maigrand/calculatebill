package com.maigrand.calculatebill.entity.bill;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class GuestPositionEntityPK implements Serializable {

    @Id
    @Column(name = "guest_id")
    private int guestId;

    @Id
    @Column(name = "position_id")
    private int positionId;
}

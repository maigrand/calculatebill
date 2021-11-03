package com.maigrand.calculatebill.entity;

import com.maigrand.calculatebill.payload.BillDetails;
import com.maigrand.calculatebill.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestEntityFactory {

    @Autowired
    protected BillService billService;

    @Autowired
    protected GuestService guestService;

    @Autowired
    protected PositionService positionService;

    /*public BillEntity createBill(String name, int tips) {
        BillDetails billDetails = new BillDetails();
        billDetails.setName(name);
        billDetails.setTips(tips);
        return this.billService.create(billDetails);
    }*/
}

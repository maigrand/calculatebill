package com.maigrand.calculatebill.view.bill;

import com.maigrand.calculatebill.entity.bill.BillEntity;
import lombok.Getter;

import java.util.Date;

@Getter
public class BillView {

    private final Integer id;

    private final String name;

    private final Integer userId;

    private final Date createdAt;

    private final Integer tipsPercent;

    private final Float totalCost;

    private final Float tipsCost;

    private final Float totalCostWithTips;

    public BillView(BillEntity billEntity) {
        this.id = billEntity.getId();
        this.name = billEntity.getName();
        this.userId = billEntity.getUserId();
        this.createdAt = billEntity.getCreatedAt();
        this.tipsPercent = billEntity.getTipsPercent();
        this.totalCost = billEntity.getTotalCost();

        float tipsCost = billEntity.getTotalCost() * billEntity.getTipsPercent() / 100;
        this.tipsCost = tipsCost;
        this.totalCostWithTips = billEntity.getTotalCost() + tipsCost;
    }
}

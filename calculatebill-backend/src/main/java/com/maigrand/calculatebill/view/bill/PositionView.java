package com.maigrand.calculatebill.view.bill;

import com.maigrand.calculatebill.entity.bill.PositionEntity;
import lombok.Getter;

@Getter
public class PositionView {

    private final Integer id;

    private final String name;

    private final Float cost;

    public PositionView(PositionEntity positionEntity) {
        this.id = positionEntity.getId();
        this.name = positionEntity.getName();
        this.cost = positionEntity.getCost();
    }
}

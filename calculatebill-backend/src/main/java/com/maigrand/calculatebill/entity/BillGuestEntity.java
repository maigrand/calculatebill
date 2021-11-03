package com.maigrand.calculatebill.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "bill_guest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BillGuestEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @EqualsAndHashCode.Exclude
    private Float totalCost;

    @DBRef
    @JsonIgnore
    private BillEntity bill;

    @DBRef
    private GuestEntity guest;

    @DBRef
    @EqualsAndHashCode.Exclude
    private List<PositionEntity> positions;

    public void addPosition(PositionEntity positionEntity) {
        this.positions.add(positionEntity);
    }
}

package com.maigrand.calculatebill.entity.bill;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "guest_position")
@IdClass(GuestPositionEntityPK.class)
@NoArgsConstructor
@Data
public class GuestPositionEntity {

    @Id
    @Column(name = "guest_id")
    @Setter(AccessLevel.NONE)
    private int guestId;

    @Id
    @Column(name = "position_id")
    @Setter(AccessLevel.NONE)
    private int positionId;

    @Column(name = "amount")
    private Float amount;

    @ManyToOne
    @JoinColumn(
            name = "guest_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false,
            insertable = false
    )
    private GuestEntity guestEntity;

    @ManyToOne
    @JoinColumn(
            name = "position_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false,
            insertable = false
    )
    private PositionEntity positionEntity;

    public void setGuestEntity(GuestEntity guestEntity) {
        this.guestEntity = guestEntity;
        this.guestId = guestEntity.getId();
    }

    public void setPositionEntity(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
        this.positionId = positionEntity.getId();
    }
}

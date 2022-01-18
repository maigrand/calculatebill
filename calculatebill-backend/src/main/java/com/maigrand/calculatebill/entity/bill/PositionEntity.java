package com.maigrand.calculatebill.entity.bill;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(
        name = "position",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "bill_id"})
        }
)
@NoArgsConstructor
@Data
public class PositionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Float cost;

    @OneToOne
    @JoinColumn(name = "bill_id")
    private BillEntity billEntity;
}

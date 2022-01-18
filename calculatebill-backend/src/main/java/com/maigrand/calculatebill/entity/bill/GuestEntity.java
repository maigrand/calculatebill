package com.maigrand.calculatebill.entity.bill;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(
        name = "guest",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "bill_id"})
        })
@NoArgsConstructor
@Data
public class GuestEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "bill_id")
    private BillEntity billEntity;
}

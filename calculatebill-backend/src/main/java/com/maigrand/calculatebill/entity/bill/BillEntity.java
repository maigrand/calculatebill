package com.maigrand.calculatebill.entity.bill;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill")
@NoArgsConstructor
@Data
public class BillEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created_at")
    @Setter(AccessLevel.NONE)
    private Date createdAt = new Date();

    @Column(name = "tips")
    private Integer tipsPercent;

    @Column(name = "total_cost")
    private Float totalCost = 0f;
}

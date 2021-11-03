package com.maigrand.calculatebill.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "guests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GuestEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @Indexed(unique = true)
    private String name;
}

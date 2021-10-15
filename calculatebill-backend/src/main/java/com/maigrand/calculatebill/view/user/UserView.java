package com.maigrand.calculatebill.view.user;

import com.maigrand.calculatebill.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserView {

    private final String id;

    private final String email;

    public UserView(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
    }
}
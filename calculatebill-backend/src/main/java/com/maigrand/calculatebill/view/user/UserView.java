package com.maigrand.calculatebill.view.user;

import com.maigrand.calculatebill.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserView {

    private final Integer id;

    private final String email;

    //todo: библиотека mapstruct
    public UserView(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
    }
}

package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.security.JwtTokenProvider;
import com.maigrand.calculatebill.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import static org.springframework.http.HttpHeaders.*;

public abstract class SecurityControllerTest extends AbstractControllerTest {

    protected String authenticationToken;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @BeforeAll
    private void setUp() {
        UserEntity userEntity = userService.create("mai@admin.com", "password", true);
        this.authenticationToken = "Bearer " + jwtTokenProvider.generateToken(userEntity.getEmail(), false);
    }

    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, this.authenticationToken);
        return headers;
    }
}

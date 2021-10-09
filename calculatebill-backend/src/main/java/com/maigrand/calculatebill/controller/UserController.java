package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.payload.user.AuthenticationCredentials;
import com.maigrand.calculatebill.payload.user.AuthenticationTokenDetails;
import com.maigrand.calculatebill.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "Пользователь")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    @GetMapping
    @ApiOperation(value = "Получить текущего пользователя (по token в header)")
    public ResponseEntity<UserEntity> currentUser(@ApiIgnore @AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "Авторизация", tags = "Авторизация")
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationCredentials authenticationCredentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationCredentials.getEmail(),
                        authenticationCredentials.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication, true);
        return ResponseEntity.ok(new AuthenticationTokenDetails(token));
    }
}

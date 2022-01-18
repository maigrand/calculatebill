package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.payload.user.*;
import com.maigrand.calculatebill.security.JwtTokenProvider;
import com.maigrand.calculatebill.service.UserService;
import com.maigrand.calculatebill.view.user.UserView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

    private final UserService userService;

    @GetMapping
    @ApiOperation(value = "Получить текущего пользователя (по token в header)")
    public UserView currentUser(@ApiIgnore @AuthenticationPrincipal UserEntity userEntity) {
        return new UserView(userEntity);
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "Авторизация", tags = "Авторизация")
    public AuthenticationTokenDetails authenticate(@RequestBody @Valid AuthenticationCredentials authenticationCredentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationCredentials.getEmail(),
                        authenticationCredentials.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication, true);
        return new AuthenticationTokenDetails(token);
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "Регистрация")
    public UserView register(@RequestBody UserDetails details) {
        return new UserView(userService.create(details));
    }
}

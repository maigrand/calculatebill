package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.GuestEntity;
import com.maigrand.calculatebill.service.GuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guest")
@Api(tags = "Гости")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping
    @ApiOperation(value = "Получить всех гостей")
    public ResponseEntity<List<GuestEntity>> show() {
        List<GuestEntity> all = this.guestService.findAll();
        return ResponseEntity.ok(all);
    }
}

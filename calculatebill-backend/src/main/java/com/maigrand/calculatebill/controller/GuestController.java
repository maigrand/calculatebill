package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.GuestEntity;
import com.maigrand.calculatebill.payload.GuestDetails;
import com.maigrand.calculatebill.service.GuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public List<GuestEntity> list() {
        return this.guestService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить гостя по id")
    public GuestEntity show(@PathVariable("id") String id) {
        return this.guestService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Добавить гостя")
    public GuestEntity create(@RequestBody GuestDetails details) {
        return this.guestService.create(details);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Редактировать гостя")
    public GuestEntity update(@PathVariable("id") String id, @RequestBody GuestDetails details) {
        return this.guestService.update(id, details);
    }
}

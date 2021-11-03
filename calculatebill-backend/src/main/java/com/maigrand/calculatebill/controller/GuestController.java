package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.GuestEntity;
import com.maigrand.calculatebill.payload.GuestDetails;
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
    public ResponseEntity<List<GuestEntity>> list() {
        List<GuestEntity> all = this.guestService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить гостя по id")
    public ResponseEntity<GuestEntity> show(@PathVariable("id") String id) {
        GuestEntity entity = this.guestService.findById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    @ApiOperation(value = "Добавить гостя")
    public ResponseEntity<GuestEntity> create(@RequestBody GuestDetails details) {
        GuestEntity entity = this.guestService.create(details);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Редактировать гостя")
    public ResponseEntity<GuestEntity> update(@PathVariable("id") String id, @RequestBody GuestDetails details) {
        GuestEntity entity = this.guestService.update(id, details);
        return ResponseEntity.ok(entity);
    }
}

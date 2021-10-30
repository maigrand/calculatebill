package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.BillEntity;
import com.maigrand.calculatebill.payload.*;
import com.maigrand.calculatebill.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
@Api(tags = "Чек")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping
    @ApiOperation(value = "Получить все чеки")
    public ResponseEntity<List<BillEntity>> list() {
        List<BillEntity> entity = this.billService.findAll();
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить чек по id")
    public ResponseEntity<BillEntity> show(@PathVariable("id") String id) {
        BillEntity entity = this.billService.findById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    @ApiOperation(value = "Создать чек")
    //todo: status created
    public ResponseEntity<BillEntity> create(@RequestBody BillDetails details) {
        BillEntity entity = this.billService.create(details);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/{id}/guest")
    @ApiOperation(value = "Добавить гостя")
    public ResponseEntity<BillEntity> addGuest(@PathVariable("id") String id, @RequestBody GuestDetails details) {
        BillEntity entity = this.billService.addGuest(id, details);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}/guest/{guestId}")
    @ApiOperation(value = "Удалить гостя")
    public ResponseEntity<BillEntity> removeGuest(
            @PathVariable("id") String id,
            @PathVariable("guestId") String guestId) {
        BillEntity entity = this.billService.removeGuest(id, guestId);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/{id}/guest/{guestId}")
    @ApiOperation(value = "Добавить гостю позицию")
    public ResponseEntity<BillEntity> addGuestPosition(
            @PathVariable("id") String id,
            @PathVariable("guestId") String guestId,
            @RequestBody PositionDetails details) {
        BillEntity entity = this.billService.addGuestPosition(id, guestId, details);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/{id}/calculate")
    @ApiOperation(value = "Подсчитать")
    public ResponseEntity<BillEntity> calculate(@PathVariable("id") String id) {
        BillEntity entity = this.billService.calculate(id);
        return ResponseEntity.ok(entity);
    }
}

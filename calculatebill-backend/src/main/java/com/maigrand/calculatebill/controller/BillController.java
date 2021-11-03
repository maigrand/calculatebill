package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.BillEntity;
import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.payload.BillDetails;
import com.maigrand.calculatebill.payload.BillGuestDetails;
import com.maigrand.calculatebill.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
@Api(tags = "Чек")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping
    @ApiOperation(value = "Получить все чеки")
    public ResponseEntity<List<BillEntity>> list(@ApiIgnore @AuthenticationPrincipal UserEntity userEntity) {
        List<BillEntity> entity = this.billService.findAll(userEntity);
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
    public ResponseEntity<BillEntity> create(
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @RequestBody BillDetails details) {
        BillEntity entity = this.billService.create(userEntity, details);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/{id}/guest")
    @ApiOperation(value = "Добавить гостя")
    public ResponseEntity<BillEntity> addGuest(@PathVariable("id") String id, @RequestBody BillGuestDetails details) {
        BillEntity entity = this.billService.addGuest(id, details);
        return ResponseEntity.ok(entity);
    }
}

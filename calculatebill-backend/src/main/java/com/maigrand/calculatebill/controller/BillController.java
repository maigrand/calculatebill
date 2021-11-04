package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.BillEntity;
import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.payload.BillDetails;
import com.maigrand.calculatebill.payload.BillGuestDetails;
import com.maigrand.calculatebill.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public List<BillEntity> list(@ApiIgnore @AuthenticationPrincipal UserEntity userEntity) {
        return this.billService.findAll(userEntity);
    }

    //todo: убрать обертку респонс ентити
    @GetMapping("/{id}")
    @ApiOperation(value = "Получить чек по id")
    public BillEntity show(@PathVariable("id") String id) {
        return this.billService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Создать чек")
    //todo: status created
    public BillEntity create(
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @RequestBody BillDetails details) {
        return this.billService.create(userEntity, details);
    }

    @PostMapping("/{id}/guest")
    @ApiOperation(value = "Добавить гостя")
    public BillEntity addGuest(@PathVariable("id") String id, @RequestBody BillGuestDetails details) {
        return this.billService.addGuest(id, details);
    }
}

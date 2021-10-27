package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.BillEntity;
import com.maigrand.calculatebill.payload.BillDetails;
import com.maigrand.calculatebill.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bill")
@Api(tags = "Чек")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    @ApiOperation(value = "Создать чек")
    public ResponseEntity<BillEntity> create(@RequestBody BillDetails details) {
        BillEntity entity = this.billService.create(details);
        return ResponseEntity.ok(entity);
    }
}

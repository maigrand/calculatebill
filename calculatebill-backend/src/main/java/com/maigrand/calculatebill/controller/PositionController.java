package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.PositionEntity;
import com.maigrand.calculatebill.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
@Api(tags = "Позиции")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    @ApiOperation(value = "Получить все позиции")
    public ResponseEntity<List<PositionEntity>> show() {
        List<PositionEntity> all = this.positionService.findAll();
        return ResponseEntity.ok(all);
    }
}

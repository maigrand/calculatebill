package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.PositionEntity;
import com.maigrand.calculatebill.payload.PositionDetails;
import com.maigrand.calculatebill.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
@Api(tags = "Позиции")
@RequiredArgsConstructor
public class PositionController {

    /*private final PositionService positionService;

    @GetMapping
    @ApiOperation(value = "Получить все позиции")
    public ResponseEntity<List<PositionEntity>> show() {
        List<PositionEntity> all = this.positionService.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить позицию")
    public ResponseEntity<PositionEntity> create(@RequestBody PositionDetails details) {
        PositionEntity entity = this.positionService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }*/
}

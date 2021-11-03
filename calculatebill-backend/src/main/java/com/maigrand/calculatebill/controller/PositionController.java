package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.PositionEntity;
import com.maigrand.calculatebill.payload.PositionDetails;
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
    public ResponseEntity<List<PositionEntity>> list() {
        List<PositionEntity> all = this.positionService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить позицию по id")
    public ResponseEntity<PositionEntity> show(@PathVariable("id") String id) {
        PositionEntity entity = this.positionService.findById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    @ApiOperation(value = "Добавить позицию")
    public ResponseEntity<PositionEntity> create(@RequestBody PositionDetails details) {
        PositionEntity entity = this.positionService.create(details);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Редактировать позицию")
    public ResponseEntity<PositionEntity> update(@PathVariable("id") String id, @RequestBody PositionDetails details) {
        PositionEntity entity = this.positionService.update(id, details);
        return ResponseEntity.ok(entity);
    }
}

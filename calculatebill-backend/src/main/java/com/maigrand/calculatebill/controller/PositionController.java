package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.PositionEntity;
import com.maigrand.calculatebill.payload.PositionDetails;
import com.maigrand.calculatebill.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public List<PositionEntity> list() {
        return this.positionService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить позицию по id")
    public PositionEntity show(@PathVariable("id") String id) {
        return this.positionService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Добавить позицию")
    public PositionEntity create(@RequestBody PositionDetails details) {
        return this.positionService.create(details);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Редактировать позицию")
    public PositionEntity update(@PathVariable("id") String id, @RequestBody PositionDetails details) {
        return this.positionService.update(id, details);
    }
}

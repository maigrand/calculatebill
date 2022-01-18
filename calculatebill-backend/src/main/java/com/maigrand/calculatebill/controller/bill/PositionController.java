package com.maigrand.calculatebill.controller.bill;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.entity.bill.BillEntity;
import com.maigrand.calculatebill.payload.PaginationDetails;
import com.maigrand.calculatebill.payload.bill.PositionDetails;
import com.maigrand.calculatebill.service.bill.BillService;
import com.maigrand.calculatebill.service.bill.PositionService;
import com.maigrand.calculatebill.view.bill.PositionView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/bill")
@Api(tags = "Позиции")
@RequiredArgsConstructor
public class PositionController {

    private final BillService billService;

    private final PositionService positionService;

    @GetMapping("/{billId}/positions")
    @ApiOperation(value = "Получить все позиции в чеке")
    public PaginationDetails<PositionView> list(
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @PathVariable("billId") Integer billId,
            Pageable pageable) {
        BillEntity billEntity = billService.findById(billId, userEntity);
        return positionService.findAll(billEntity, pageable).map(PositionView::new);
    }

    @PostMapping("/{billId}/positions")
    @ApiOperation(value = "Создать позицию в чеке")
    public PositionView create(
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @PathVariable("billId") Integer billId,
            @RequestBody PositionDetails details) {
        BillEntity billEntity = billService.findById(billId, userEntity);
        return new PositionView(positionService.create(billEntity, details));
    }
}

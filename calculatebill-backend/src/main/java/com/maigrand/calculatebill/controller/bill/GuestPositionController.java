package com.maigrand.calculatebill.controller.bill;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.entity.bill.BillEntity;
import com.maigrand.calculatebill.entity.bill.GuestEntity;
import com.maigrand.calculatebill.payload.bill.GuestPositionDetails;
import com.maigrand.calculatebill.service.bill.*;
import com.maigrand.calculatebill.view.bill.GuestPositionView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/bill")
@Api(tags = "Позиции гостя в чеке")
@RequiredArgsConstructor
public class GuestPositionController {

    private final BillService billService;

    private final GuestService guestService;

    private final GuestPositionService guestPositionService;

    @GetMapping("/{billId}/guests/{guestId}/positions")
    @ApiOperation(value = "Получить позиции гостя в чеке")
    public GuestPositionView show(
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @PathVariable("billId") Integer billId,
            @PathVariable("guestId") Integer guestId) {
        BillEntity billEntity = billService.findById(billId, userEntity);
        GuestEntity guestEntity = guestService.findById(guestId);
        return new GuestPositionView(guestPositionService.findAllByGuestEntity(billEntity, guestEntity));
    }

    @PostMapping("/{billId}/guests/{guestId}/positions")
    @ApiOperation(value = "Добавить позицию гостю в чеке")
    public GuestPositionView create(
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @PathVariable("billId") Integer billId,
            @PathVariable("guestId") Integer guestId,
            @RequestBody GuestPositionDetails details) {
        BillEntity billEntity = billService.findById(billId, userEntity);
        GuestEntity guestEntity = guestService.findById(guestId);
        return new GuestPositionView(guestPositionService.create(billEntity, guestEntity, details));
    }
}

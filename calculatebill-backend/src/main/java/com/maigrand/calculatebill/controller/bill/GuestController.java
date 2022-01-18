package com.maigrand.calculatebill.controller.bill;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.entity.bill.BillEntity;
import com.maigrand.calculatebill.entity.bill.GuestEntity;
import com.maigrand.calculatebill.payload.PaginationDetails;
import com.maigrand.calculatebill.payload.bill.GuestDetails;
import com.maigrand.calculatebill.service.bill.BillService;
import com.maigrand.calculatebill.service.bill.GuestService;
import com.maigrand.calculatebill.view.bill.GuestView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/bill")
@Api(tags = "Гости")
@RequiredArgsConstructor
public class GuestController {

    private final BillService billService;

    private final GuestService guestService;

    @GetMapping("{billId}/guests")
    @ApiOperation(value = "Получить всех гостей в чеке")
    public PaginationDetails<GuestView> list(
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @PathVariable("billId") Integer billId,
            Pageable pageable) {
        BillEntity billEntity = billService.findById(billId, userEntity);
        PaginationDetails<GuestEntity> all = guestService.findAll(billEntity, pageable);
        return all.map(GuestView::new);
    }

    @PostMapping("{billId}/guests")
    @ApiOperation(value = "Создать гостя в чеке")
    public GuestView create(
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @PathVariable("billId") Integer billId,
            @RequestBody GuestDetails details) {
        BillEntity billEntity = billService.findById(billId, userEntity);
        return new GuestView(guestService.create(billEntity, details));
    }
}

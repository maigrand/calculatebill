package com.maigrand.calculatebill.controller.bill;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.payload.PaginationDetails;
import com.maigrand.calculatebill.payload.bill.BillDetails;
import com.maigrand.calculatebill.service.bill.BillService;
import com.maigrand.calculatebill.view.bill.BillView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/bill")
@Api(tags = "Чек")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping
    @ApiOperation(value = "Получить все чеки")
    public PaginationDetails<BillView> list(@ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            Pageable pageable) {
        return billService.findAll(userEntity, pageable).map(BillView::new);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить чек по id")
    public BillView show(@PathVariable("id") Integer id,
            @ApiIgnore @AuthenticationPrincipal UserEntity userEntity) {
        return new BillView(billService.findById(id, userEntity));
    }

    @PostMapping
    @ApiOperation(value = "Создать чек")
    public BillView create(@ApiIgnore @AuthenticationPrincipal UserEntity userEntity,
            @RequestBody BillDetails details) {
        return new BillView(billService.create(userEntity, details));
    }
}

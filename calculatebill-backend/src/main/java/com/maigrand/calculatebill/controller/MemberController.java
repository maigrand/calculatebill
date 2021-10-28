package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.entity.MemberEntity;
import com.maigrand.calculatebill.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@Api(tags = "Участники")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @ApiOperation(value = "Получить всех участников")
    public ResponseEntity<List<MemberEntity>> show() {
        List<MemberEntity> all = this.memberService.findAll();
        return ResponseEntity.ok(all);
    }
}

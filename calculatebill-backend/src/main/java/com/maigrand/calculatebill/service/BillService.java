package com.maigrand.calculatebill.service;

import com.maigrand.calculatebill.entity.*;
import com.maigrand.calculatebill.payload.BillDetails;
import com.maigrand.calculatebill.repository.BillRepository;
import com.maigrand.calculatebill.validator.group.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class BillService {

    private final MemberService memberService;

    private final BillRepository billRepository;

    public List<BillEntity> findAll() {
        return this.billRepository.findAll();
    }

    @Validated(OnCreate.class)
    public BillEntity create(BillDetails details) {
        BillEntity entity = new BillEntity();

        Set<MemberEntity> memberEntitySet = details.getMemberNames().stream()
                .map(this.memberService::findByName)
                .collect(Collectors.toSet());

        Float totalCost = memberEntitySet.stream()
                .flatMap((memberEntity -> memberEntity.getPositions().stream()
                        .map(PositionEntity::getCost))).reduce(Float::sum).get();

        entity.setMembers(memberEntitySet);
        entity.setDate(new Date());
        entity.setTips(details.getTips());
        entity.setTotalCost(totalCost);
        entity.setName(details.getName());

        return entity;
    }
}

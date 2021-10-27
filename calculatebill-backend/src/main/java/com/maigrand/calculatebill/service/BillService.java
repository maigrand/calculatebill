package com.maigrand.calculatebill.service;

import com.maigrand.calculatebill.entity.*;
import com.maigrand.calculatebill.exception.EntityNotFoundException;
import com.maigrand.calculatebill.payload.*;
import com.maigrand.calculatebill.repository.BillRepository;
import com.maigrand.calculatebill.validator.group.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class BillService {

    private final MemberService memberService;

    private final PositionService positionService;

    private final BillRepository billRepository;

    public BillEntity findById(String id) {
        return this.billRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("bill not found"));
    }

    public List<BillEntity> findAll() {
        return this.billRepository.findAll();
    }

    @Validated(OnCreate.class)
    public BillEntity create(@Valid BillDetails details) {
        BillEntity entity = new BillEntity();

        Set<MemberEntity> memberEntitySet = details.getMemberNames().stream()
                .map(this.memberService::findByName)
                .collect(Collectors.toSet());

        Float totalCost = memberEntitySet.stream()
                .flatMap((memberEntity -> memberEntity.getPositions().stream()
                        .map(PositionEntity::getCost))).reduce(Float::sum).get();

        entity.setMembers(memberEntitySet);
        entity.setTips(details.getTips());
        entity.setTotalCost(totalCost);
        entity.setName(details.getName());

        return entity;
    }

    public BillEntity addMember(String id, @Valid MemberDetails details) {
        BillEntity billEntity = findById(id);
        MemberEntity memberEntity = this.memberService.create(details);
        billEntity.addMember(memberEntity);
        return this.billRepository.save(billEntity);
    }

    public BillEntity addMemberPosition(String id, String memberId, PositionDetails details) {
        BillEntity billEntity = findById(id);
        MemberEntity memberEntity=this.memberService.findById(memberId);
        PositionEntity positionEntity = this.positionService.create(details);
        memberEntity.addPosition(positionEntity);
        this.memberService.save(memberEntity);
        return this.billRepository.save(billEntity);
    }
}

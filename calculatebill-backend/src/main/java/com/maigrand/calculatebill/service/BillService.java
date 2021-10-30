package com.maigrand.calculatebill.service;

import com.maigrand.calculatebill.entity.*;
import com.maigrand.calculatebill.exception.EntityNotFoundException;
import com.maigrand.calculatebill.payload.*;
import com.maigrand.calculatebill.repository.BillRepository;
import com.maigrand.calculatebill.validator.group.OnCreate;
import com.maigrand.calculatebill.validator.group.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

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

        entity.setName(details.getName());
        Optional.ofNullable(details.getTips()).ifPresent(entity::setTips);

        return this.billRepository.save(entity);
    }

    @Validated(OnUpdate.class)
    public BillEntity update(String id, @Valid BillDetails details) {
        BillEntity entity = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(entity::setName);
        Optional.ofNullable(details.getTips()).ifPresent(entity::setTips);
        return this.billRepository.save(entity);
    }

    public BillEntity addMember(String id, @Valid MemberDetails details) {
        BillEntity billEntity = findById(id);
        MemberEntity memberEntity = this.memberService.findByNameOrCreate(details);
        billEntity.addMember(memberEntity);
        return this.billRepository.save(billEntity);
    }

    public BillEntity addMemberPosition(String id, String memberId, @Valid PositionDetails details) {
        BillEntity billEntity = findById(id);

        PositionEntity positionEntity = this.positionService.findByNameOrCreate(details);

        MemberEntity memberEntity = this.memberService.findById(memberId);
        memberEntity.addPosition(positionEntity);
        memberEntity = this.memberService.save(memberEntity);

        billEntity.removeMember(memberEntity);
        billEntity.addMember(memberEntity);
        billEntity = this.billRepository.save(billEntity);

        return billEntity;
    }

    public BillEntity removeMember(String id, String memberId) {
        BillEntity billEntity = findById(id);
        MemberEntity memberEntity = this.memberService.findById(memberId);
        billEntity.removeMember(memberEntity);
        return this.billRepository.save(billEntity);
    }

    public BillEntity calculate(String id) {
        BillEntity billEntity = findById(id);
        Set<BillMemberPojo> billMemberPojoList = new HashSet<>();

        Float totalCost = billEntity.getMembers().stream()
                .flatMap(memberEntity -> memberEntity.getPositions().stream()
                        .map(PositionEntity::getCost)).reduce(Float::sum).get();

        totalCost = totalCost + (totalCost * billEntity.getTips() / 100);

        for (MemberEntity member : billEntity.getMembers()) {
            Float cost = member.getPositions().stream()
                    .map(PositionEntity::getCost)
                    .reduce(Float::sum).get();

            member.setPositions(new ArrayList<>());
            this.memberService.save(member);

            BillMemberPojo billMemberPojo = new BillMemberPojo();
            billMemberPojo.setMemberName(member.getName());
            billMemberPojo.setCost(cost + (cost * billEntity.getTips() / 100));
            billMemberPojoList.add(billMemberPojo);
        }

        billEntity.setBillMemberPojoSet(billMemberPojoList);
        billEntity.setTotalCost(totalCost);
        billEntity.setMembers(new HashSet<>());
        this.billRepository.save(billEntity);

        return billEntity;
    }
}

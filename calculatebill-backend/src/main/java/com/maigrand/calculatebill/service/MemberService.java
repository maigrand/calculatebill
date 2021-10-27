package com.maigrand.calculatebill.service;

import com.maigrand.calculatebill.entity.MemberEntity;
import com.maigrand.calculatebill.entity.PositionEntity;
import com.maigrand.calculatebill.exception.EntityExistsException;
import com.maigrand.calculatebill.exception.EntityNotFoundException;
import com.maigrand.calculatebill.payload.MemberDetails;
import com.maigrand.calculatebill.repository.MemberRepository;
import com.maigrand.calculatebill.validator.group.OnCreate;
import com.maigrand.calculatebill.validator.group.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class MemberService {

    private final PositionService positionService;

    private final MemberRepository memberRepository;

    public MemberEntity findById(String id) {
        return this.memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("member not found"));
    }

    public List<MemberEntity> findAll() {
        return this.memberRepository.findAll();
    }

    public MemberEntity findByName(String name) {
        return this.memberRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("member not found"));
    }

    @Validated(OnCreate.class)
    public MemberEntity create(MemberDetails details) {
        if (this.memberRepository.existsByName(details.getName())) {
            throw new EntityExistsException("member exists");
        }
        MemberEntity entity = new MemberEntity();

        Set<PositionEntity> positions = details.getPositions().stream()
                .map(this.positionService::findByName)
                .collect(Collectors.toSet());

        entity.setName(details.getName());
        entity.setPositions(positions);

        return this.memberRepository.save(entity);
    }

    @Validated(OnUpdate.class)
    public MemberEntity update(MemberDetails details) {
        if (!this.memberRepository.existsByName(details.getName())) {
            throw new EntityNotFoundException("member not found");
        }
        MemberEntity entity = this.memberRepository.findByName(details.getName()).get();

        Optional.ofNullable(details.getName()).ifPresent(entity::setName);
        return this.memberRepository.save(entity);
    }

    public void delete(String name) {
        Optional<MemberEntity> byName = this.memberRepository.findByName(name);
        if (byName.isPresent()) {
            this.memberRepository.delete(byName.get());
        } else {
            throw new EntityNotFoundException("member not found");
        }
    }

    public MemberEntity save(MemberEntity memberEntity){
        return this.memberRepository.save(memberEntity);
    }
}

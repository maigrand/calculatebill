package com.maigrand.calculatebill.service;

import com.maigrand.calculatebill.entity.GuestEntity;
import com.maigrand.calculatebill.exception.EntityExistsException;
import com.maigrand.calculatebill.exception.EntityNotFoundException;
import com.maigrand.calculatebill.payload.GuestDetails;
import com.maigrand.calculatebill.repository.GuestRepository;
import com.maigrand.calculatebill.validator.group.OnCreate;
import com.maigrand.calculatebill.validator.group.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public List<GuestEntity> findAll() {
        return this.guestRepository.findAll();
    }

    public GuestEntity findById(String id) {
        return this.guestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("member not found"));
    }

    @Validated(OnCreate.class)
    public GuestEntity create(GuestDetails details) {
        if (this.guestRepository.existsByName(details.getName())) {
            throw new EntityExistsException("member exists");
        }
        GuestEntity entity = new GuestEntity();

        entity.setName(details.getName());

        return this.guestRepository.save(entity);
    }

    @Validated(OnUpdate.class)
    public GuestEntity update(String id, @Valid GuestDetails details) {
        GuestEntity entity = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(entity::setName);
        return this.guestRepository.save(entity);
    }
}

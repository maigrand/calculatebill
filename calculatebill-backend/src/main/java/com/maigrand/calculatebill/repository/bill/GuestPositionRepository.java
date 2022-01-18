package com.maigrand.calculatebill.repository.bill;

import com.maigrand.calculatebill.entity.bill.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestPositionRepository extends JpaRepository<GuestPositionEntity, GuestPositionEntityPK> {

    List<GuestPositionEntity> findAllByGuestEntity(GuestEntity guestEntity);

    Optional<GuestPositionEntity> findByGuestEntityAndPositionEntity(
            GuestEntity guestEntity,
            PositionEntity positionEntity);
}

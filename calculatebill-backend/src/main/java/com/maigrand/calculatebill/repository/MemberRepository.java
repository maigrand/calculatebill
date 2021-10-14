package com.maigrand.calculatebill.repository;

import com.maigrand.calculatebill.entity.MemberEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<MemberEntity, String> {

    boolean existsByName(String name);
    Optional<MemberEntity> findByName(String name);
}

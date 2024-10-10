package com.kau.capstone.domain.member.repository;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnedPetRepository extends JpaRepository<OwnedPet, Long> {

    @Query("SELECT o.pet FROM OwnedPet o WHERE o.member = :member")
    List<Pet> findPetsByMember(@Param("member") Member member);
}

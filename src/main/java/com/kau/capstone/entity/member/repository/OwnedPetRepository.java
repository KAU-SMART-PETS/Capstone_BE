package com.kau.capstone.entity.member.repository;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.OwnedPet;
import com.kau.capstone.entity.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OwnedPetRepository extends JpaRepository<OwnedPet, Long> {

    @Query("SELECT o.pet FROM OwnedPet o WHERE o.member = :member")
    List<Pet> findPetsByMember(@Param("member") Member member);


    @Query("SELECT op.pet FROM OwnedPet op WHERE op.member.id = :memberId AND op.pet.id = :petId")
    Optional<Pet> findPetByMemberAndPetId(@Param("memberId") Long memberId, @Param("petId") Long petId);

    @Query("SELECT COUNT(o) > 0 FROM OwnedPet o WHERE o.member = :member AND o.pet = :pet")
    Boolean existsByMemberAndPet(@Param("member") Member member, @Param("pet") Pet pet);

}

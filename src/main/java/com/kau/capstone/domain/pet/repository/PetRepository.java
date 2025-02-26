package com.kau.capstone.domain.pet.repository;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetAndMemberNotMatchedException;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p WHERE p.member = :member")
    List<Pet> findByMember(@Param("member") Member member);

    @Query("SELECT p FROM Pet p WHERE p.id = :id AND p.deletedAt IS NULL AND p.member = :member")
    Optional<Pet> findByIdAndDeletedAtIsNullAndMember(@Param("id") Long id, @Param("member") Member member);

    default Pet getByIdAndDeletedAtIsNullAndMember(Long id, Member member) {
        return findByIdAndDeletedAtIsNullAndMember(id, member).orElseThrow(
            PetAndMemberNotMatchedException::new);
    }
}

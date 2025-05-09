package com.kau.capstone.entity.pet.repository;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.v2.pet.exception.PetNotFoundExceptionV2;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Deprecated
    @Query("SELECT p FROM Pet p WHERE p.member = :member")
    List<Pet> findByMember(@Param("member") Member member);

    @Query("SELECT p FROM Pet p WHERE p.id = :id AND p.deletedAt IS NULL AND p.member = :member")
    Optional<Pet> findByIdAndMemberAndDeletedAtIsNull(@Param("id") Long id, @Param("member") Member member);

    default Pet getByIdAndMemberAndDeletedAtIsNull(Long id, Member member) {
        return findByIdAndMemberAndDeletedAtIsNull(id, member).orElseThrow(
            PetNotFoundExceptionV2::new
        );
    }
}

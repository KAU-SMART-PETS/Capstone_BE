package com.kau.capstone.domain.vaccination.repository;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.vaccination.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    @Query("SELECT v FROM Vaccination v WHERE v.member = :member AND v.pet = :type")
    List<Vaccination> findAllByMemberAndPet(@Param("member") Member member,
                                            @Param("pet") Pet pet);
}

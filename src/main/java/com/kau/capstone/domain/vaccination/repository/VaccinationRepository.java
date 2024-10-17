package com.kau.capstone.domain.vaccination.repository;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.vaccination.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    @Query("SELECT v FROM Vaccination v WHERE v.pet = :pet ORDER BY v.timeYear DESC, v.timeMonth DESC, v.timeDay DESC")
    List<Vaccination> findAllByMemberAndPet(@Param("pet") Pet pet);
}

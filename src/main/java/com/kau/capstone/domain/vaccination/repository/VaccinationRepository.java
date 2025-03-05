package com.kau.capstone.domain.vaccination.repository;

import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.vaccination.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findAllByPetOrderByVaccinatedAtDesc(@Param("pet") Pet pet);
}

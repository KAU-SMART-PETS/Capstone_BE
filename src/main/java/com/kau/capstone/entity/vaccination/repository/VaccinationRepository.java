package com.kau.capstone.entity.vaccination.repository;

import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.vaccination.Vaccination;
import com.kau.capstone.domain.vaccination.exception.VaccinationNotFoundExceptionV2;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findAllByPetOrderByVaccinatedAtDesc(@Param("pet") Pet pet);

    default @NonNull Vaccination getById(@NonNull Long id) {
        return findById(id).orElseThrow(VaccinationNotFoundExceptionV2::new);
    }
}

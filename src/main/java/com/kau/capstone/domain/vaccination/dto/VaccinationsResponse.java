package com.kau.capstone.domain.vaccination.dto;

import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.vaccination.entity.Vaccination;

import java.util.List;

public record VaccinationsResponse(
        PetVaccinationResponse pet,
        List<OneVaccinationResponse> vaccination
) {

    public static VaccinationsResponse toResponse(Pet pet, List<Vaccination> vaccinations) {
        PetVaccinationResponse petResponse = PetVaccinationResponse.toResponse(pet);
        List<OneVaccinationResponse> vaccinationResponses = vaccinations.stream()
                .map(OneVaccinationResponse::toResponse)
                .toList();

        return new VaccinationsResponse(petResponse, vaccinationResponses);
    }
}

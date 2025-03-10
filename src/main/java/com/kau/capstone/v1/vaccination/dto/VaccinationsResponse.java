package com.kau.capstone.v1.vaccination.dto;

import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.vaccination.Vaccination;
import lombok.Builder;

import java.util.List;

@Builder
public record VaccinationsResponse(
        PetVaccinationResponse pet,
        List<OneVaccinationResponse> vaccination
) {

    public static VaccinationsResponse toResponse(Pet pet, List<Vaccination> vaccinations) {
        PetVaccinationResponse petResponse = PetVaccinationResponse.toResponse(pet);
        List<OneVaccinationResponse> vaccinationResponses = vaccinations.stream()
                .map(OneVaccinationResponse::toResponse)
                .toList();

        return VaccinationsResponse.builder()
                .pet(petResponse)
                .vaccination(vaccinationResponses)
                .build();
    }
}

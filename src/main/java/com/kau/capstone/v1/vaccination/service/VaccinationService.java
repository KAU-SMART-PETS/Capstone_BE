package com.kau.capstone.v1.vaccination.service;

import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.v1.pet.exception.PetNotFoundException;
import com.kau.capstone.entity.pet.repository.PetRepository;
import com.kau.capstone.v1.vaccination.dto.CreateVaccinationRequest;
import com.kau.capstone.v1.vaccination.dto.PutVaccinationRequest;
import com.kau.capstone.v1.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.entity.vaccination.Vaccination;
import com.kau.capstone.v1.vaccination.exception.VaccinationNotFoundException;
import com.kau.capstone.entity.vaccination.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.VACCINATION_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class VaccinationService {

    private final MemberRepository memberRepository;
    private final PetRepository petRepository;
    private final VaccinationRepository vaccinationRepository;

    public VaccinationsResponse getVaccinationInfo(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException());

        List<Vaccination> vaccinations = vaccinationRepository.findAllByPetOrderByVaccinatedAtDesc(pet);

        return VaccinationsResponse.toResponse(pet, vaccinations);
    }

    public void createVaccinationInfo(Long petId, CreateVaccinationRequest request) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException());

        Vaccination vaccination = Vaccination.of(pet, request);
        vaccinationRepository.save(vaccination);
    }

    public void putVaccinationInfo(Long vaccinationId, PutVaccinationRequest request) {
        Vaccination vaccination = vaccinationRepository.findById(vaccinationId)
                .orElseThrow(() -> new VaccinationNotFoundException(VACCINATION_NOT_FOUND));

        vaccination.modify(request);
    }

    public void deleteVaccinationInfo(Long vaccinationId) {
        Vaccination vaccination = vaccinationRepository.findById(vaccinationId)
                .orElseThrow(() -> new VaccinationNotFoundException(VACCINATION_NOT_FOUND));

        vaccinationRepository.delete(vaccination);
    }
}

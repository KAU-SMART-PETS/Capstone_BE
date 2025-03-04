package com.kau.capstone.domain.vaccination.service;

import static com.kau.capstone.global.exception.ErrorCode.PET_INFO_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.VACCINATION_NOT_FOUND;

import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.vaccination.dto.CreateVaccinationRequestV2;
import com.kau.capstone.domain.vaccination.dto.PutVaccinationRequest;
import com.kau.capstone.domain.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.domain.vaccination.entity.Vaccination;
import com.kau.capstone.domain.vaccination.exception.VaccinationNotFoundException;
import com.kau.capstone.domain.vaccination.repository.VaccinationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VaccinationServiceV2 {

    private final MemberRepository memberRepository;
    private final PetRepository petRepository;
    private final VaccinationRepository vaccinationRepository;

    @Transactional
    public void createVaccinationInfo(Long petId, CreateVaccinationRequestV2 request) {
        Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new PetNotFoundException(PET_INFO_NOT_FOUND));

        Vaccination vaccination = Vaccination.of(pet, request);
        vaccinationRepository.save(vaccination);
    }

//    public VaccinationsResponse getVaccinationInfo(Long petId) {
//        Pet pet = petRepository.findById(petId)
//                .orElseThrow(() -> new PetNotFoundException(PET_INFO_NOT_FOUND));
//
//        List<Vaccination> vaccinations = vaccinationRepository.findAllByPet(pet);
//
//        return VaccinationsResponse.toResponse(pet, vaccinations);
//    }
//
//    public void putVaccinationInfo(Long vaccinationId, PutVaccinationRequest request) {
//        Vaccination vaccination = vaccinationRepository.findById(vaccinationId)
//                .orElseThrow(() -> new VaccinationNotFoundException(VACCINATION_NOT_FOUND));
//
//        vaccination.modify(request);
//    }
//
    public void deleteVaccinationInfo(Long vaccinationId) {
        Vaccination vaccination = vaccinationRepository.getById(vaccinationId);
        vaccinationRepository.delete(vaccination);
    }

}

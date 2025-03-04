package com.kau.capstone.domain.vaccination.service;

import static com.kau.capstone.global.exception.ErrorCode.PET_INFO_NOT_FOUND;

import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.vaccination.dto.CreateVaccinationRequestV2;
import com.kau.capstone.domain.vaccination.dto.PutVaccinationRequestV2;
import com.kau.capstone.domain.vaccination.dto.VaccinationsResponseV2;
import com.kau.capstone.domain.vaccination.entity.Vaccination;
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

    public VaccinationsResponseV2 getVaccinationInfo(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(PET_INFO_NOT_FOUND));

        List<Vaccination> vaccinations = vaccinationRepository.findAllByPet(pet);
        return VaccinationsResponseV2.of(pet, vaccinations);
    }

    @Transactional
    public void putVaccinationInfo(Long vaccinationId, PutVaccinationRequestV2 request) {
        Vaccination vaccination = vaccinationRepository.getById(vaccinationId);
        vaccination.modify(request);
    }

    @Transactional
    public void deleteVaccinationInfo(Long vaccinationId) {
        Vaccination vaccination = vaccinationRepository.getById(vaccinationId);
        vaccinationRepository.delete(vaccination);
    }

}

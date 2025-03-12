package com.kau.capstone.v1.vaccination.service;

import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.pet.repository.PetRepository;
import com.kau.capstone.v1.pet.exception.PetNotFoundException;
import com.kau.capstone.v1.vaccination.dto.CreateVaccinationReqV2;
import com.kau.capstone.v1.vaccination.dto.PutVaccinationReqV2;
import com.kau.capstone.v1.vaccination.dto.VaccinationsResV2;
import com.kau.capstone.entity.vaccination.Vaccination;
import com.kau.capstone.entity.vaccination.repository.VaccinationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VaccinationServiceV2 {

    private final PetRepository petRepository;
    private final VaccinationRepository vaccinationRepository;

    @Transactional
    public void createVaccinationInfo(Long petId, CreateVaccinationReqV2 request) {
        Pet pet = petRepository.findById(petId)
            .orElseThrow(PetNotFoundException::new);

        Vaccination vaccination = Vaccination.of(pet, request);
        vaccinationRepository.save(vaccination);
    }

    @Transactional(readOnly = true)
    public VaccinationsResV2 getVaccinationInfo(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(PetNotFoundException::new);

        List<Vaccination> vaccinations = vaccinationRepository.findAllByPetOrderByVaccinatedAtDesc(pet);
        return VaccinationsResV2.of(pet, vaccinations);
    }

    @Transactional
    public void putVaccinationInfo(Long vaccinationId, PutVaccinationReqV2 request) {
        Vaccination vaccination = vaccinationRepository.getById(vaccinationId);
        vaccination.modify(request);
    }

    @Transactional
    public void deleteVaccinationInfo(Long vaccinationId) {
        Vaccination vaccination = vaccinationRepository.getById(vaccinationId);
        vaccinationRepository.delete(vaccination);
    }

}

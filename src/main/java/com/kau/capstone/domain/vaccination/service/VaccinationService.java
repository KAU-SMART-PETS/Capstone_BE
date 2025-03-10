package com.kau.capstone.domain.vaccination.service;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.vaccination.dto.CreateVaccinationRequest;
import com.kau.capstone.domain.vaccination.dto.PutVaccinationRequest;
import com.kau.capstone.domain.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.domain.vaccination.entity.Vaccination;
import com.kau.capstone.domain.vaccination.exception.VaccinationNotFoundException;
import com.kau.capstone.domain.vaccination.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.PET_INFO_NOT_FOUND;
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

        List<Vaccination> vaccinations = vaccinationRepository.findAllByMemberAndPet(pet);

        return VaccinationsResponse.toResponse(pet, vaccinations);
    }

    public void createVaccinationInfo(Long memberId, Long petId, CreateVaccinationRequest request) {
        Member member = memberRepository.getById(memberId);

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException());

        Vaccination vaccination = Vaccination.builder()
                .member(member)
                .pet(pet)
                .name(request.name())
                .timeYear(request.year())
                .timeMonth(request.month())
                .timeDay(request.day())
                .build();
        vaccinationRepository.save(vaccination);
    }

    public void putVaccinationInfo(Long vaccinationId, PutVaccinationRequest request) {
        Vaccination vaccination = vaccinationRepository.findById(vaccinationId)
                .orElseThrow(() -> new VaccinationNotFoundException(VACCINATION_NOT_FOUND));

        vaccination.modify(request.name(), request.year(), request.month(), request.day());
    }

    public void deleteVaccinationInfo(Long vaccinationId) {
        Vaccination vaccination = vaccinationRepository.findById(vaccinationId)
                .orElseThrow(() -> new VaccinationNotFoundException(VACCINATION_NOT_FOUND));

        vaccinationRepository.delete(vaccination);
    }
}

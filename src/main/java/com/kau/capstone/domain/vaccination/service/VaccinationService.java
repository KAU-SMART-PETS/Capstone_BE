package com.kau.capstone.domain.vaccination.service;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.domain.vaccination.entity.Vaccination;
import com.kau.capstone.domain.vaccination.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.PET_INFO_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class VaccinationService {

    private final MemberRepository memberRepository;
    private final PetRepository petRepository;
    private final VaccinationRepository vaccinationRepository;

    public VaccinationsResponse getVaccinationInfo(Long memberId, Long petId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(PET_INFO_NOT_FOUND));

        List<Vaccination> vaccinations = vaccinationRepository.findAllByMemberAndPet(member, pet);

        return VaccinationsResponse.toResponse(pet, vaccinations);
    }
}

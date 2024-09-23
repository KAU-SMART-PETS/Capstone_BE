package com.kau.capstone.domain.pet.service;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.dto.request.UpdatePetInfoRequest;
import com.kau.capstone.domain.pet.dto.response.PetInfoResponse;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.pet.util.PetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kau.capstone.global.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;
    private final OwnedPetRepository ownedPetRepository;

    @Transactional
    public void registPet(LoginInfo loginInfo, PetRegistRequest petRegistRequest) {
        Pet pet = petRepository.save(PetMapper.toPet(petRegistRequest));
        savedOwnedPet(loginInfo.memberId(), pet);
    }

    private void savedOwnedPet(Long memberId, Pet pet) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(MEMBER_NOT_FOUND)
        );

        OwnedPet ownedPet = OwnedPet.builder()
                .member(member)
                .pet(pet)
                .build();

        ownedPetRepository.save(ownedPet);
    }

    public Pet findByPetId(Long petId) {
        return petRepository.findById(petId).orElseThrow(
            () -> new PetNotFoundException(PET_INFO_NOT_FOUND)
        );
    }

    public PetInfoResponse getPetInfo(Long petId) {

        Pet pet = findByPetId(petId);

        return PetMapper.toGetResponseDTO(pet);
    }

    @Transactional
    public void updatePetInfo(Long petId, UpdatePetInfoRequest updatePetInfoRequest) {
        Pet pet = findByPetId(petId);
        pet.updatePet(updatePetInfoRequest);
        petRepository.save(pet);
    }

    @Transactional
    public void deletePetInfo(Long petId) {
        Pet pet = findByPetId(petId);
        pet.deletePet();
        petRepository.save(pet);
    }
}

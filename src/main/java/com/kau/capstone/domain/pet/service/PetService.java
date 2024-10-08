package com.kau.capstone.domain.pet.service;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.PET_INFO_NOT_FOUND;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.dto.response.PetInfoResponse;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.pet.util.PetMapper;
import com.kau.capstone.global.common.s3.S3Service;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final S3Service s3Service;

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;
    private final OwnedPetRepository ownedPetRepository;

    @Transactional
    public void createPetInfo(LoginInfo loginInfo, PetRegistRequest petRegistRequest)
        throws IOException {
        Pet pet = PetMapper.toPet(petRegistRequest);
        petRepository.save(pet);
        savedOwnedPet(loginInfo.memberId(), pet);
        uploadImage(petRegistRequest, pet);
    }

    @Transactional
    public void updatePetInfo(Long petId, PetRegistRequest petRegistRequest) throws IOException {
        Pet pet = findByPetId(petId);
        pet.updatePet(petRegistRequest);
        uploadImage(petRegistRequest, pet);
        petRepository.save(pet);
    }

    @Transactional
    public void deletePetInfo(Long petId) {
        Pet pet = findByPetId(petId);
        pet.deletePet();
        s3Service.delete(pet);
        petRepository.save(pet);
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

    @Transactional(readOnly = true)
    public PetInfoResponse getPetInfo(Long petId) {
        Pet pet = findByPetId(petId);
        return PetMapper.toGetResponseDTO(pet);
    }

    private void uploadImage(PetRegistRequest petRegistRequest, Pet pet) throws IOException {
        if (!Objects.isNull(petRegistRequest.getImage())) {
            String dirName = pet.getId().toString() + "/profile";
            String imageUrl = s3Service.upload(petRegistRequest.getImage(), dirName,
                "profileImage");
            pet.updateImageUrl(imageUrl);
        }
    }
}

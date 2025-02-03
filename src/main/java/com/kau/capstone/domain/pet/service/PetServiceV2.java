package com.kau.capstone.domain.pet.service;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.pet.util.PetMapperV2;
import com.kau.capstone.global.common.s3.S3Service;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Service
@RequiredArgsConstructor
public class PetServiceV2 {
    private final S3Service s3Service;
    private final PetRepository petRepository;
    private final MemberRepository memberRepository;
    private final OwnedPetRepository ownedPetRepository;
    private Member findMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
            () -> new MemberNotFoundException("member not found")
        );
    }

    @Transactional
    public void createPetInfo(LoginInfo loginInfo, PetRegistReqV2 petRegistReqV2)
        throws IOException {
        log.info(petRegistReqV2.getName());
        Pet pet = savePet(petRegistReqV2);
        saveOwnedPet(loginInfo.memberId(), pet);
        uploadImage(petRegistReqV2.getImage(), pet);
    }

    private Pet savePet(PetRegistReqV2 petRegistReqV2){
        Pet pet = PetMapperV2.toPet(petRegistReqV2);
        petRepository.save(pet);
        return pet;
    }

    private void saveOwnedPet(Long memberId, Pet pet){
        Member member = findMemberById(memberId);
        OwnedPet ownedPet = PetMapperV2.toOwnedPet(member, pet);
        ownedPetRepository.save(ownedPet);
    }

    private void uploadImage(MultipartFile file, Pet pet) throws IOException {
        if (!Objects.isNull(file)) {
            String dirName = pet.getId().toString() + "/profile";
            String imageUrl = s3Service.upload(file, dirName, "profileImage");
            pet.updateImageUrl(imageUrl);
        }
    }
}

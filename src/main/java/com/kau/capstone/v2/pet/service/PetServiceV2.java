package com.kau.capstone.v2.pet.service;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.pet.repository.PetRepository;
import com.kau.capstone.global.common.s3.FileService;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v2.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.v2.pet.dto.request.PetReqV2;
import com.kau.capstone.v2.pet.dto.response.OwnedPetsResV2;
import com.kau.capstone.v2.pet.dto.response.PetResV2;
import com.kau.capstone.v2.pet.util.PetMapperV2;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PetServiceV2 {

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Transactional
    public void createPetInfo(LoginInfo loginInfo, PetRegistReqV2 petRegistReqV2) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = PetMapperV2.toPet(petRegistReqV2, member);
        petRepository.save(pet);

        String dirName = pet.getId() + "/profile/";
        String imageUrl = fileService.uploadImage(petRegistReqV2.getImage(), dirName);
        pet.updateImageUrl(imageUrl);
        petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    public PetResV2 getPetInfo(LoginInfo loginInfo, Long petId) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = petRepository.getByIdAndMemberAndDeletedAtIsNull(petId, member);
        return PetResV2.toResponse(pet);
    }

    @Transactional(readOnly = true)
    public OwnedPetsResV2 getPetsInfo(LoginInfo loginInfo) {
        Member member = memberRepository.getById(loginInfo.memberId());
        List<Pet> pets = petRepository.findByMember(member);
        return OwnedPetsResV2.toResponse(pets);
    }

    @Transactional
    public void updatePetInfo(LoginInfo loginInfo, Long petId, PetReqV2 petRequest) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = petRepository.getByIdAndMemberAndDeletedAtIsNull(petId, member);
        pet.updatePetV2(petRequest);
        petRepository.save(pet);
    }

    @Transactional
    public void updatePetImage(LoginInfo loginInfo, Long petId, MultipartFile image) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = petRepository.getByIdAndMemberAndDeletedAtIsNull(petId, member);
        String dirName = petId + "/profile";
        String url = fileService.uploadImage(image, dirName);
        pet.updateImageUrl(url);
        petRepository.save(pet);
    }

    @Transactional
    public void deletePet(LoginInfo loginInfo, Long petId) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = petRepository.getByIdAndMemberAndDeletedAtIsNull(petId, member);
        pet.deletePet();
        petRepository.save(pet);
    }


}

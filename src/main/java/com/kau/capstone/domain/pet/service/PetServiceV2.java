package com.kau.capstone.domain.pet.service;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.dto.response.PetResV2;
import com.kau.capstone.domain.pet.dto.response.PetsResV2;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetAndMemberNotMatchedException;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.pet.util.PetMapperV2;
import com.kau.capstone.global.common.s3.S3Service;
import java.io.IOException;
import java.util.List;
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

    private Pet findPetById(Long petId){
        return petRepository.findByIdAndDeletedAtIsNull(petId).orElseThrow(
            () -> new PetNotFoundException("pet not found")
        );
    }

    private void checkOwnedPetByMember(Member member, Pet pet){
        if(!ownedPetRepository.existsByMemberAndPet(member, pet))
            throw new PetAndMemberNotMatchedException("pet and member not matched");
    }

    @Transactional
    public void createPetInfo(LoginInfo loginInfo, PetRegistReqV2 petRegistReqV2)
        throws IOException {
        log.info(petRegistReqV2.getName());
        Pet pet = savePet(petRegistReqV2);
        saveOwnedPet(loginInfo.memberId(), pet);
        uploadImage(petRegistReqV2.getImage(), pet);
    }

    @Transactional(readOnly = true)
    public PetResV2 getPetInfo(LoginInfo loginInfo, Long petId){
        Member member = this.findMemberById(loginInfo.memberId());
        Pet pet = this.findPetById(petId);
        checkOwnedPetByMember(member, pet);
        return PetMapperV2.toPetResV2Dto(pet);
    }

    @Transactional(readOnly = true)
    public List<PetsResV2> getPetsInfo(LoginInfo loginInfo){
        Member member = this.findMemberById(loginInfo.memberId());
        List<Pet> pets = ownedPetRepository.findPetsByMember(member);
        return PetMapperV2.toPetsRes(pets);
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

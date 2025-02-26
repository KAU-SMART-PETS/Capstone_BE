package com.kau.capstone.domain.pet.service;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.PET_INFO_NOT_FOUND;

import com.kau.capstone.domain.alarm.entity.Alarm;
import com.kau.capstone.domain.alarm.entity.AlarmDetail;
import com.kau.capstone.domain.alarm.repository.AlarmRepository;
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
import com.kau.capstone.domain.reward.entity.Reward;
import com.kau.capstone.domain.reward.entity.RewardDetail;
import com.kau.capstone.domain.reward.repository.RewardRepository;
import com.kau.capstone.global.common.s3.FileService;
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
    private final RewardRepository rewardRepository;
    private final AlarmRepository alarmRepository;
    private final FileService fileService;

    @Transactional
    public void createPetInfo(LoginInfo loginInfo, PetRegistRequest petRegistRequest)
        throws IOException {
        Pet pet = PetMapper.toPet(petRegistRequest);
        petRepository.save(pet);
        savedOwnedPet(loginInfo.memberId(), pet);
        uploadImage(petRegistRequest, pet);
        achievedCreatePetReward(loginInfo.memberId());
        notVisibleCreatePetAlarm(loginInfo.memberId());
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
        fileService.deleteImage(pet.getImageUrl());
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
            () -> new PetNotFoundException()
        );
    }

    @Transactional(readOnly = true)
    public PetInfoResponse getPetInfo(Long petId) {
        Pet pet = findByPetId(petId);
        return PetMapper.toGetResponseDTO(pet);
    }

    private void uploadImage(PetRegistRequest petRegistRequest, Pet pet) {
        if (!Objects.isNull(petRegistRequest.getImage())) {
            String dirName = pet.getId() + "/profile";
            String imageUrl = fileService.uploadImage(petRegistRequest.getImage(), dirName);
            pet.updateImageUrl(imageUrl);
        }
    }

    // 반려동물 등록하기 리워드 성공
    private void achievedCreatePetReward(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        Reward reward = rewardRepository.findRewardByMemberAndType(member, RewardDetail.ONE.type());
        if (!Objects.isNull(reward) && !reward.getIsAchieved()) {
            reward.achievedSuccess();
        }
    }

    // 반려동물 알람 제거
    private void notVisibleCreatePetAlarm(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        Alarm alarm = alarmRepository.findAlarmByMemberAndType(member, AlarmDetail.ONE.type());
        if (!Objects.isNull(alarm) && alarm.getIsVisible()) {
            alarm.doNotVisible();
        }
    }
}

package com.kau.capstone.domain.pet.service;

import com.kau.capstone.domain.pet.dto.request.PetRegistRequest;
import com.kau.capstone.domain.pet.dto.request.UpdatePetInfoRequest;
import com.kau.capstone.domain.pet.dto.response.PetInfoResponse;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.exception.PetNotFoundException;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.pet.util.PetMapper;
import com.kau.capstone.global.common.s3.S3Service;
import com.kau.capstone.global.exception.ErrorCode;
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

    private final PetRepository petRepository;
    private final S3Service s3Service;

    public Pet getPet(Long petId) {
        return petRepository.findById(petId).orElseThrow(
            () -> new PetNotFoundException(ErrorCode.PET_INFO_NOT_FOUND)
        );
    }

    @Transactional
    public void createPetInfo(PetRegistRequest petRegistRequest)
        throws IOException {
        Pet pet = PetMapper.toPet(petRegistRequest);
        petRepository.save(pet);
        log.info(petRegistRequest.getImage().getOriginalFilename());
        if (!Objects.isNull(petRegistRequest.getImage())) {
            String dirName = pet.getId().toString() + "/profile";
            String imageUrl = s3Service.upload(petRegistRequest.getImage(), dirName, "profile");
            pet.updateImageUrl(imageUrl);
        }
    }

    @Transactional(readOnly = true)
    public PetInfoResponse getPetInfo(Long petId) {
        Pet pet = getPet(petId);
        return PetMapper.toGetResponseDTO(pet);
    }

    @Transactional
    public void updatePetInfo(Long petId, UpdatePetInfoRequest updatePetInfoRequest) {
        Pet pet = getPet(petId);
        pet.updatePet(updatePetInfoRequest);
        petRepository.save(pet);
    }

    @Transactional
    public void deletePetInfo(Long petId) {
        Pet pet = getPet(petId);
        pet.deletePet();
        petRepository.save(pet);
    }
}

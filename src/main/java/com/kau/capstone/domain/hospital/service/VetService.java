package com.kau.capstone.domain.hospital.service;

import com.kau.capstone.domain.hospital.dto.VetResponse;
import com.kau.capstone.domain.hospital.entity.Vet;
import com.kau.capstone.domain.hospital.exception.VetNotFoundException;
import com.kau.capstone.domain.hospital.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kau.capstone.global.exception.ErrorCode.VET_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService {

    private final VetRepository vetRepository;

    public VetResponse getVetInfo(Long vetId) {
        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new VetNotFoundException(VET_NOT_FOUND));

        return VetResponse.toResponse(vet);
    }
}

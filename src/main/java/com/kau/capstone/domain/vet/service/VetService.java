package com.kau.capstone.domain.vet.service;

import com.kau.capstone.domain.vet.dto.MemberLocationRequest;
import com.kau.capstone.domain.vet.dto.VetDetailResponse;
import com.kau.capstone.domain.vet.dto.VetsResponse;
import com.kau.capstone.domain.vet.entity.Vet;
import com.kau.capstone.domain.vet.exception.VetNotFoundException;
import com.kau.capstone.domain.vet.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.VET_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService {

    private final VetRepository vetRepository;

    public VetDetailResponse getVetInfo(Long vetId, MemberLocationRequest request) {
        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new VetNotFoundException(VET_NOT_FOUND));
        Double vetToMemberDistance = vet.calculateDistanceToMember(request.latitude(), request.longitude());

        return VetDetailResponse.toResponse(vet, vetToMemberDistance);
    }

    public VetsResponse getVetsInfo() {
        List<Vet> vets = vetRepository.findAll();

        return VetsResponse.toResponse(vets);
    }
}

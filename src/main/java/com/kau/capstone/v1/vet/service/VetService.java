package com.kau.capstone.v1.vet.service;

import com.kau.capstone.v1.vet.dto.MemberLocationRequest;
import com.kau.capstone.v1.vet.dto.VetDetailResponse;
import com.kau.capstone.v1.vet.dto.VetsResponse;
import com.kau.capstone.entity.vet.Vet;
import com.kau.capstone.v1.vet.exception.VetNotFoundException;
import com.kau.capstone.entity.vet.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.VET_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    public VetDetailResponse getVetDetailInfo(Long vetId, MemberLocationRequest request) {
        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new VetNotFoundException(VET_NOT_FOUND));
        Double vetToMemberDistance = vet.calculateDistanceToMember(request.latitude(), request.longitude());

        return VetDetailResponse.toResponse(vet, vetToMemberDistance);
    }

    public VetsResponse getVetsInfo(MemberLocationRequest request) {
        List<Vet> vets = vetRepository.findNearestVets(request.latitude(), request.longitude());

        return VetsResponse.toResponse(vets);
    }
}

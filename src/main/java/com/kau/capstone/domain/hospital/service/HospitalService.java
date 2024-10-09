package com.kau.capstone.domain.hospital.service;

import com.kau.capstone.domain.hospital.dto.HospitalResponse;
import com.kau.capstone.domain.hospital.entity.Hospital;
import com.kau.capstone.domain.hospital.exception.HospitalNotFoundException;
import com.kau.capstone.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kau.capstone.global.exception.ErrorCode.HOSPITAL_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalResponse getHospitalInfo(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException(HOSPITAL_NOT_FOUND));

        return HospitalResponse.toResponse(hospital);
    }
}

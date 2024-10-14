package com.kau.capstone.domain.bluetooth.service;

import com.kau.capstone.domain.bluetooth.dto.OwnedBluetoothResponse;
import com.kau.capstone.domain.bluetooth.entity.Bluetooth;
import com.kau.capstone.domain.bluetooth.repository.OwnedBluetoothRepository;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class BluetoothService {

    private final MemberRepository memberRepository;
    private final OwnedBluetoothRepository ownedBluetoothRepository;

    public OwnedBluetoothResponse getOwnedBluetoothInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        List<Bluetooth> bluetooth = ownedBluetoothRepository.findBluetoothByMember(member);

        return OwnedBluetoothResponse.toResponse(bluetooth);
    }
}

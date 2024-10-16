package com.kau.capstone.domain.bluetooth.service;

import com.kau.capstone.domain.bluetooth.dto.BluetoothRegistryRequest;
import com.kau.capstone.domain.bluetooth.dto.OwnedBluetoothResponse;
import com.kau.capstone.domain.bluetooth.entity.Bluetooth;
import com.kau.capstone.domain.bluetooth.entity.member.OwnedBluetooth;
import com.kau.capstone.domain.bluetooth.repository.BluetoothRepository;
import com.kau.capstone.domain.bluetooth.repository.OwnedBluetoothRepository;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.reward.entity.Reward;
import com.kau.capstone.domain.reward.entity.RewardDetail;
import com.kau.capstone.domain.reward.repository.RewardRepository;
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
    private final BluetoothRepository bluetoothRepository;
    private final OwnedBluetoothRepository ownedBluetoothRepository;
    private final RewardRepository rewardRepository;

    public void registryOwnedBluetooth(Long memberId, BluetoothRegistryRequest request) {
        Bluetooth bluetooth = Bluetooth.builder()
                .name(request.name())
                .macAddress(request.macAddress())
                .build();
        bluetoothRepository.save(bluetooth);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        OwnedBluetooth ownedBluetooth = OwnedBluetooth.builder()
                .member(member)
                .bluetooth(bluetooth)
                .build();
        ownedBluetoothRepository.save(ownedBluetooth);

        Reward reward = rewardRepository.findRewardByMemberAndType(member, RewardDetail.TWO.type());
        if (reward.getIsAchieved()) {
            reward.achievedSuccess();
        }
    }

    public OwnedBluetoothResponse getOwnedBluetoothInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        List<Bluetooth> bluetooth = ownedBluetoothRepository.findBluetoothByMember(member);

        return OwnedBluetoothResponse.toResponse(bluetooth);
    }
}

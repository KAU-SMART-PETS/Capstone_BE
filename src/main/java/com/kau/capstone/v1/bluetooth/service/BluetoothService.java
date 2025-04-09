package com.kau.capstone.v1.bluetooth.service;

import com.kau.capstone.v1.bluetooth.dto.BluetoothRegistryRequest;
import com.kau.capstone.v1.bluetooth.dto.OwnedBluetoothResponse;
import com.kau.capstone.entity.bluetooth.Bluetooth;
import com.kau.capstone.entity.bluetooth.OwnedBluetooth;
import com.kau.capstone.entity.bluetooth.repository.BluetoothRepository;
import com.kau.capstone.entity.bluetooth.repository.OwnedBluetoothRepository;
import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.reward.Reward;
import com.kau.capstone.entity.reward.RewardDetail;
import com.kau.capstone.entity.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

        Member member = memberRepository.getById(memberId);

        OwnedBluetooth ownedBluetooth = OwnedBluetooth.builder()
                .member(member)
                .bluetooth(bluetooth)
                .build();
        ownedBluetoothRepository.save(ownedBluetooth);

        Reward reward = rewardRepository.findRewardByMemberAndType(member, RewardDetail.TWO.type());
        if (!Objects.isNull(reward) && !reward.getIsAchieved()) {
            reward.achievedSuccess();
        }
    }

    public OwnedBluetoothResponse getOwnedBluetoothInfo(Long memberId) {
        Member member = memberRepository.getById(memberId);

        List<Bluetooth> bluetooth = ownedBluetoothRepository.findBluetoothByMember(member);

        return OwnedBluetoothResponse.toResponse(bluetooth);
    }
}

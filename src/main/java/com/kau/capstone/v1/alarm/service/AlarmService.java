package com.kau.capstone.v1.alarm.service;

import com.kau.capstone.v1.alarm.dto.AlarmResponse;
import com.kau.capstone.entity.alarm.Alarm;
import com.kau.capstone.entity.alarm.repository.AlarmRepository;
import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmService {

    private final MemberRepository memberRepository;
    private final AlarmRepository alarmRepository;

    public AlarmResponse getAlarmInfo(Long memberId) {
        Member member = memberRepository.getById(memberId);

        List<Alarm> alarms = alarmRepository.findAllAlarmByMember(member);

        return AlarmResponse.toResponse(alarms);
    }
}

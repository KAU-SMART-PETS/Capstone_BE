package com.kau.capstone.domain.alarm.service;

import com.kau.capstone.domain.alarm.dto.AlarmResponse;
import com.kau.capstone.domain.alarm.entity.Alarm;
import com.kau.capstone.domain.alarm.repository.AlarmRepository;
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
public class AlarmService {

    private final MemberRepository memberRepository;
    private final AlarmRepository alarmRepository;

    public AlarmResponse getAlarmInfo(Long memberId) {
        Member member = memberRepository.getById(memberId);

        List<Alarm> alarms = alarmRepository.findAllAlarmByMember(member);

        return AlarmResponse.toResponse(alarms);
    }
}

package com.kau.capstone.domain.point.service;

import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.exception.PointNotEnoughException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.point.dto.EarnPointRequest;
import com.kau.capstone.domain.point.dto.PayPointRequest;
import com.kau.capstone.domain.point.entity.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.POINT_NOT_ENOUGH;

@Service
@Transactional
@RequiredArgsConstructor
public class PointService {

    private final MemberRepository memberRepository;

    public void processPointPayment(Long memberId, PayPointRequest request) {
        Point point = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND))
                .getPoint();

        if (point.getAmount() < request.point()) {
            throw new PointNotEnoughException(POINT_NOT_ENOUGH);
        }

        point.payment(request.point());
    }

    public void processPointEarn(Long memberId, EarnPointRequest request) {
        Point point = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND))
                .getPoint();

        point.deposit(request.point());
    }
}

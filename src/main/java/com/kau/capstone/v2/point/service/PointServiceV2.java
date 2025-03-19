package com.kau.capstone.v2.point.service;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.point.History;
import com.kau.capstone.entity.point.Point;
import com.kau.capstone.entity.point.PointType;
import com.kau.capstone.entity.point.repository.HistoryRepository;
import com.kau.capstone.entity.point.repository.PointRepository;
import com.kau.capstone.v2.point.dto.request.DepositPointReqV2;
import com.kau.capstone.v2.point.dto.request.PayPointReqV2;
import com.kau.capstone.v2.point.dto.response.HistoryListResV2;
import com.kau.capstone.v2.point.exception.PointNotEnoughExceptionV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointServiceV2 {

    private final MemberRepository memberRepository;
    private final HistoryRepository historyRepository;
    private final PointRepository pointRepository;

    @Transactional
    public void payPoint(long memberId, PayPointReqV2 request) {
        Member member = memberRepository.getById(memberId);
        Point point = pointRepository.getByMember(member);

        checkAmount(point.getAmount(), request.point());

        point.payment(request.point());

        History history = History.of(point, request, PointType.PAYMENT_POINT);
        historyRepository.save(history);
    }

    @Transactional
    public void depositPoint(long memberId, DepositPointReqV2 request) {
        Member member = memberRepository.getById(memberId);
        Point point = pointRepository.getByMember(member);

        point.deposit(request.point());

        History history = History.of(point, request, PointType.PAYMENT_POINT);
        historyRepository.save(history);
    }

    @Transactional(readOnly = true)
    public HistoryListResV2 getPointHistory(long memberId) {
        Member member = memberRepository.getById(memberId);
        return historyRepository.findHistoryListByMember(member);
    }

    private void checkAmount(long amount, long point){
        if (amount < point) {
            throw new PointNotEnoughExceptionV2();
        }
    }
}

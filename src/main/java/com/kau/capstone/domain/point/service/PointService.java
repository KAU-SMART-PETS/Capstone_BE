package com.kau.capstone.domain.point.service;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.exception.PointNotEnoughException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.point.dto.EarnPointRequest;
import com.kau.capstone.domain.point.dto.HistoryResponse;
import com.kau.capstone.domain.point.dto.PayPointRequest;
import com.kau.capstone.domain.point.entity.Point;
import com.kau.capstone.domain.point.entity.history.History;
import com.kau.capstone.domain.point.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.POINT_NOT_ENOUGH;

@Service
@Transactional
@RequiredArgsConstructor
public class PointService {

    private static final String PAYMENT_POINT = "포인트 결제";
    private static final String EARN_POINT = "포인트 적립";

    private final MemberRepository memberRepository;
    private final HistoryRepository historyRepository;

    public void processPointPayment(Long memberId, PayPointRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        Point point = member.getPoint();

        if (point.getAmount() < request.point()) {
            throw new PointNotEnoughException(POINT_NOT_ENOUGH);
        }

        point.payment(request.point());
        save(member, point, -request.point(), PAYMENT_POINT);
    }

    public void processPointEarn(Long memberId, EarnPointRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        Point point = member.getPoint();

        point.deposit(request.point());
        save(member, point, request.point(), EARN_POINT);
    }

    public HistoryResponse getPointHistory(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        List<History> histories = historyRepository.findHistoriesByMember(member);

        return HistoryResponse.toResponse(histories);
    }

    public void save(Member member, Point point, Long changePoint, String name) {
        History history = History.builder()
                .member(member)
                .point(point)
                .totalPoint(point.getAmount())
                .changePoint(changePoint)
                .name(name)
                .date(LocalDateTime.now())
                .build();
        historyRepository.save(history);
    }
}

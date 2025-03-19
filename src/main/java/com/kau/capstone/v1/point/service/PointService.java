package com.kau.capstone.v1.point.service;

import com.kau.capstone.entity.alarm.Alarm;
import com.kau.capstone.entity.alarm.AlarmDetail;
import com.kau.capstone.entity.alarm.repository.AlarmRepository;
import com.kau.capstone.entity.food.Food;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.v1.food.exception.FoodNotFoundException;
import com.kau.capstone.entity.food.repository.FoodRepository;
import com.kau.capstone.entity.member.Member;
import com.kau.capstone.v1.member.exception.PointNotEnoughException;
import com.kau.capstone.v1.point.dto.EarnPointRequest;
import com.kau.capstone.v1.point.dto.HistoryResponse;
import com.kau.capstone.v1.point.dto.PayPointRequest;
import com.kau.capstone.entity.point.Point;
import com.kau.capstone.entity.point.History;
import com.kau.capstone.entity.point.repository.HistoryRepository;
import com.kau.capstone.entity.reward.Reward;
import com.kau.capstone.entity.reward.RewardDetail;
import com.kau.capstone.v1.reward.exception.RewardNotFoundException;
import com.kau.capstone.entity.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.kau.capstone.global.exception.ErrorCode.FOOD_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.POINT_NOT_ENOUGH;
import static com.kau.capstone.global.exception.ErrorCode.REWARD_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class PointService {

    private static final String PAYMENT_POINT = "포인트 결제";
    private static final String EARN_POINT = "포인트 적립";

    private final MemberRepository memberRepository;
    private final HistoryRepository historyRepository;
    private final FoodRepository foodRepository;
    private final RewardRepository rewardRepository;
    private final AlarmRepository alarmRepository;

    public void processPointPayment(long memberId, PayPointRequest request) {
        Member member = memberRepository.getById(memberId);

        Point point = member.getPoint();

        if (point.getAmount() < request.point()) {
            throw new PointNotEnoughException(POINT_NOT_ENOUGH);
        }

        point.payment(request.point());
        save(member, point, -request.point(), PAYMENT_POINT);
    }

    public void processPointEarn(long memberId, EarnPointRequest request) {
        Member member = memberRepository.getById(memberId);

        Point point = member.getPoint();

        point.deposit(request.point());
        save(member, point, request.point(), EARN_POINT);
    }

    public HistoryResponse getPointHistory(long memberId) {
        Member member = memberRepository.getById(memberId);

        List<History> histories = historyRepository.findHistoriesByMember(member);

        return HistoryResponse.toResponse(histories);
    }

    public void save(Member member, Point point, Long changePoint, String name) {
        History history = History.of(member, point, changePoint, name);
        historyRepository.save(history);
    }

    public void processPointPaymentForFood(long memberId, Long foodId, Long deliveryFee) {
        Member member = memberRepository.getById(memberId);
        Point point = member.getPoint();

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException(FOOD_NOT_FOUND));
        Long totalPrice = food.getPrice() + deliveryFee;

        if (point.getAmount() < totalPrice) {
            throw new PointNotEnoughException(POINT_NOT_ENOUGH);
        }

        point.payment(totalPrice);
        save(member, point, -totalPrice, food.getName());

        Reward reward = rewardRepository.findRewardByMemberAndType(member, RewardDetail.THREE.type());
        if (!Objects.isNull(reward) && !reward.getIsAchieved()) {
            reward.achievedSuccess();
        }

        Alarm alarm = alarmRepository.findAlarmByMemberAndType(member, AlarmDetail.ONE.type());
        if (!Objects.isNull(alarm) && alarm.getIsVisible()) {
            alarm.doNotVisible();
        }
    }

    public void processPointEarnForReward(long memberId, Long rewardId) {
        Member member = memberRepository.getById(memberId);
        Point point = member.getPoint();

        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new RewardNotFoundException(REWARD_NOT_FOUND));
        Long rewardPoint = reward.getEarnPoint();

        point.deposit(rewardPoint);
        save(member, point, rewardPoint, reward.getTitle());

        if (!reward.getIsObtain()) {
            reward.obtainSuccess();
        }
    }
}

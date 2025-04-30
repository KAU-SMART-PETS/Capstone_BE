package com.kau.capstone.v2.food.service;

import com.kau.capstone.entity.alarm.Alarm;
import com.kau.capstone.entity.alarm.AlarmDetail;
import com.kau.capstone.entity.alarm.repository.AlarmRepository;
import com.kau.capstone.entity.food.Food;
import com.kau.capstone.entity.food.repository.FoodRepository;
import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.point.History;
import com.kau.capstone.entity.point.Point;
import com.kau.capstone.entity.point.repository.HistoryRepository;
import com.kau.capstone.entity.reward.Reward;
import com.kau.capstone.entity.reward.RewardDetail;
import com.kau.capstone.entity.reward.repository.RewardRepository;
import com.kau.capstone.v2.food.dto.response.FoodsResponseV2;
import com.kau.capstone.v2.point.exception.PointNotEnoughExceptionV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodServiceV2 {

    private final FoodRepository foodRepository;
    private final MemberRepository memberRepository;
    private final RewardRepository rewardRepository;
    private final AlarmRepository alarmRepository;
    private final HistoryRepository historyRepository;

    public FoodsResponseV2 getFoodsInfo() {
        List<Food> foods = foodRepository.findAll();

        return FoodsResponseV2.of(foods);
    }

    public void processPointPaymentForFood(Long memberId, Long foodId, Long deliveryFee) {
        Member member = memberRepository.getById(memberId);
        Point point = member.getPoint();

        Food food = foodRepository.getById(foodId);
        long totalPrice = food.getPrice() + deliveryFee;

        checkAmount(point.getAmount(), totalPrice);

        point.payment(totalPrice);
        History history = History.of(point, -totalPrice, food.getName());
        historyRepository.save(history);

        Reward reward = rewardRepository.findRewardByMemberAndType(member, RewardDetail.THREE.type());
        if (!Objects.isNull(reward) && !reward.getIsAchieved()) {
            reward.achievedSuccess();
        }

        Alarm alarm = alarmRepository.findAlarmByMemberAndType(member, AlarmDetail.ONE.type());
        if (!Objects.isNull(alarm) && alarm.getIsVisible()) {
            alarm.doNotVisible();
        }
    }

    private void checkAmount(long amount, long point){
        if (amount < point) {
            throw new PointNotEnoughExceptionV2();
        }
    }
}

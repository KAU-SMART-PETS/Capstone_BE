package com.kau.capstone.domain.walk.entity;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Walk extends BaseEntity {

    private static final Double UNIT_STEP_PER_METER = 1.2; // 1M당 1.2보로 측정
    private static final Double UNIT_KCAL_PER_STEP = 0.04; // 한 걸음당 0.04kcal로 측정

    @Id
    @Comment("산책 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("산책한 사용자 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Comment("거리")
    private Double distance;

    @Comment("소모 칼로리")
    private Double kcal;

    @Comment("걸음 수")
    private Long steps;

    @Comment("소모 시간")
    private LocalDateTime walkingTime;

    public Walk(Member member, Double distance, LocalDateTime walkingTime) {
        Long steps = calculateSteps(distance);
        Double kcal = calculateKcal(steps);

        this.member = member;
        this.distance = distance;
        this.kcal = kcal;
        this.steps = steps;
        this.walkingTime = walkingTime;
    }

    public static Walk create(Member member, Double distance, LocalDateTime walkingTime) {
        return new Walk(member, distance, walkingTime);
    }

    /*
    100M당 120보 기준으로 책정함. 1M당 1.2보로 계산 진행
     */
    private Long calculateSteps(Double distance) {
        return (long) (distance * UNIT_STEP_PER_METER);
    }

    /*
    1걸음당 0.04kcal로 책정
     */
    private Double calculateKcal(Long steps) {
        return steps * UNIT_KCAL_PER_STEP;
    }
}

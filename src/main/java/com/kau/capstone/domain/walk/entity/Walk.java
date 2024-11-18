package com.kau.capstone.domain.walk.entity;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Walk extends BaseEntity {

    private static final Double UNIT_STEP_PER_METER = 1.2; // 1M당 1.2보로 측정
    private static final Double UNIT_KCAL_PER_STEP = 0.04; // 한 걸음당 0.04kcal로 측정

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("산책한 사용자 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Comment("측정 일자")
    private Date dataIntDt;

    @Comment("이동 거리")
    private Double distance;

    @Comment("소모 칼로리")
    private Double kcal;

    @Comment("걸음 수")
    private Long step;

    @Comment("소요 시간")
    private String walkingTime;

    private Double tLux;

    private Double avgK;

    private Double avgLux;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private static Double calculateKcal(Long step) {
        return step * UNIT_KCAL_PER_STEP;
    }

    public static Walk create(Member member, Pet pet, Date dataIntDt, Double distance, Long step, String walkingTime, Double tLux, Double avgK, Double avgLux, LocalDateTime startTime, LocalDateTime endTime) {
        Double kcal = calculateKcal(step);
        return Walk.builder()
                .member(member)
                .pet(pet)
                .dataIntDt(dataIntDt)
                .distance(distance)
                .step(step)
                .walkingTime(walkingTime)
                .tLux(tLux)
                .avgK(avgK)
                .avgLux(avgLux)
                .startTime(startTime)
                .endTime(endTime)
                .kcal(kcal)
                .build();
    }

}

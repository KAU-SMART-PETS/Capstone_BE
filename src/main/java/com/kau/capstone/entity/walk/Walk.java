package com.kau.capstone.entity.walk;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.global.common.BaseEntity;
import com.kau.capstone.v1.walk.dto.request.WalkRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Walk extends BaseEntity {

//    private static final Double UNIT_STEP_PER_METER = 1.2; // 1M당 1.2보로 측정
    private static final Double UNIT_KCAL_PER_STEP = 0.04; // 한 걸음당 0.04kcal로 측정

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Comment("측정 일자")
    @Column
    private LocalDate dataIntDt;

    @Comment("이동 거리")
    @Column
    private Double distance;

    @Comment("산책 시간")
    @Column
    private Long walkingTime;

    @Comment("소모 칼로리")
    @Column
    private Double kcal;

    @Comment("걸음 수")
    @Column
    private Long step;

    @Embedded
    @Comment("조도량 정보")
    private WalkLightStats walkLightStats;

    @Embedded
    @Comment("산책 기간")
    private WalkTime walkTime;

    private Walk(Member member, Pet pet, LocalDate dataIntDt, Double distance, Long walkingTime, Double kcal, Long step, WalkLightStats walkLightStats, WalkTime walkTime) {
        this.member = member;
        this.pet = pet;
        this.dataIntDt = dataIntDt;
        this.distance = distance;
        this.walkingTime = walkingTime;
        this.kcal = kcal;
        this.step = step;
        this.walkLightStats = walkLightStats;
        this.walkTime = walkTime;
    }

    // V1 전용 팩토리 메소드
    public static Walk of(Member member, Pet pet, WalkRequest walkRequest) {
        WalkLightStats walkLightStats = WalkLightStats.of(walkRequest.getTLux(),walkRequest.getAvgK(),walkRequest.getAvgLux());
        WalkTime walkTime = WalkTime.of(walkRequest.getStartTime(),walkRequest.getEndTime());
        Double kcal = calcKcal(walkRequest.getStep());
        return new Walk(member, pet, walkRequest.getDataInpDt(), walkRequest.getDistance(), walkRequest.getWalkingTime(), kcal, walkRequest.getStep(), walkLightStats, walkTime);
    }

    private static Double calcKcal(Long step) {
        return UNIT_KCAL_PER_STEP * step;
    }

}

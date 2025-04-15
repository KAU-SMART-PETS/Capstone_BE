package com.kau.capstone.entity.walk;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.global.common.BaseEntity;
import com.kau.capstone.v1.walk.dto.request.WalkRequest;
import com.kau.capstone.v2.walk.dto.request.WalkCreateReqV2;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Walk extends BaseEntity {

    private static final double UNIT_STEP_PER_METER = 2.0; // 1M당 2보로 측정
    private static final double UNIT_KCAL_PER_STEP = 0.04; // 한 걸음당 0.04kcal로 측정

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Comment("산책 시작 시간")
    private LocalDateTime startTime;

    @Comment("산책 종료 시간")
    private LocalDateTime endTime;

    @Comment("산책 시간")
    private long duration;

    @Comment("이동 거리")
    private double distance;

    @Comment("소모 칼로리")
    private double kcal;

    @Comment("걸음 수")
    private long step;


    // V1 생성자
    private Walk(Member member, Pet pet, WalkRequest walkReq) {
        this.member = member;
        this.pet = pet;
        this.startTime = walkReq.getStartTime();
        this.endTime = walkReq.getEndTime();
        this.duration = Duration.between(startTime, endTime).getSeconds();
        this.distance = walkReq.getDistance();
        this.kcal = calcKcal(walkReq.getDistance());
        this.step = calcStep(walkReq.getDistance());
    }

    // V2 생성자
    private Walk(Member member, Pet pet, WalkCreateReqV2 walkCreateReq) {
        this.member = member;
        this.pet = pet;
        this.startTime = walkCreateReq.startTime();
        this.endTime = walkCreateReq.endTime();
        this.duration = Duration.between(startTime, endTime).getSeconds();
        this.distance = walkCreateReq.distance();
        this.kcal = calcKcal(walkCreateReq.distance());
        this.step = calcStep(walkCreateReq.distance());
    }

    // V1 생성자 메소드
    public static Walk of(Member member, Pet pet, WalkRequest walkReq) {
        return new Walk(member, pet, walkReq);
    }

    // V2 생성자 메소드
    public static Walk of(Member member, Pet pet, WalkCreateReqV2 walkCreateReq) {
        return new Walk(member, pet, walkCreateReq);
    }

    // 걸음수 계산 메소드
    private static long calcStep(double distance){
        return (long)(distance * UNIT_STEP_PER_METER);
    }

    // 칼로리 계산 메소드
    private static double calcKcal(double distance) {
        return calcStep(distance) * UNIT_KCAL_PER_STEP;
    }

    // 산책 시간 계산 메소드
    public static long calcWalkTime(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.getSeconds();
    }

}

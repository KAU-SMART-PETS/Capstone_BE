package com.kau.capstone.entity.point;

import com.kau.capstone.entity.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    @Comment("결제 내역 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Comment("포인트 연결")
    @JoinColumn(name = "point_id")
    private Point point;

    @ManyToOne
    @Comment("사용자 연결")
    @JoinColumn(name = "member_id")
    private Member member;

    @Comment("적립/결제 후의 총 포인트")
    private Long totalPoint;

    @Comment("적립/결제하는 포인트")
    private Long changePoint;

    @Comment("무엇으로 인해 적립/결제했는지 보여주는 내용")
    private String name;

    @Comment("적립/결제 일자")
    private LocalDateTime date;
}

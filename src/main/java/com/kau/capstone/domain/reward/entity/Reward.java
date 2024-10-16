package com.kau.capstone.domain.reward.entity;

import com.kau.capstone.domain.member.entity.Member;
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

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reward {

    @Id
    @Comment("리워드 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Comment("사용자 연결")
    @JoinColumn(name = "member_id")
    private Member member;

    @Comment("리워드 종류 식별자")
    private Long type;

    @Comment("리워드 제목")
    private String title;

    @Comment("리워드 세부 내용")
    private String content;

    @Comment("획득 포인트")
    private Long earnPoint;

    @Comment("달성 여부")
    private Boolean isAchieved;
}

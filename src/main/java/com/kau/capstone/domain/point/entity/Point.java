package com.kau.capstone.domain.point.entity;

import com.kau.capstone.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Point {

    @Id
    @Comment("포인트 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "point")
    private Member member;

    @Comment("포인트 값")
    private Long amount;

    public void connectMember(Member member) {
        this.member = member;
    }

    public void payment(Long payPoint) {
        this.amount -= payPoint;
    }

    public void deposit(Long depositPoint) {
        this.amount += depositPoint;
    }
}

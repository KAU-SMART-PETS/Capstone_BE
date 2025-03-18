package com.kau.capstone.entity.point;

import com.kau.capstone.entity.member.Member;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {
    @Id
    @Comment("포인트 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "point")
    private Member member;

    @Comment("포인트 값")
    private long amount;

    private Point(Member member) {
        this.member = member;
        this.amount = 0L;
    }

    public static Point of(Member member) {
        return new Point(member);
    }

    public void payment(long payPoint) {
        this.amount -= payPoint;
    }

    public void deposit(long depositPoint) {
        this.amount += depositPoint;
    }
}

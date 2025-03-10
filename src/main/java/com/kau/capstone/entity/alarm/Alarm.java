package com.kau.capstone.entity.alarm;

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

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {

    @Id
    @Comment("알람 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Comment("사용자 연결")
    @JoinColumn(name = "member_id")
    private Member member;

    @Comment("알람 식별자")
    private Long type;

    @Comment("알람 내용")
    private String text;

    @Comment("알람 표기 여부")
    private Boolean isVisible;

    public void doNotVisible() {
        this.isVisible = false;
    }
}

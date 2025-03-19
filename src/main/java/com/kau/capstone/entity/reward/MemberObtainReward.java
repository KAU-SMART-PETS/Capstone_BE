package com.kau.capstone.entity.reward;

import com.kau.capstone.entity.member.Member;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberObtainReward {

    @EmbeddedId
    private MemberRewardId id;

    @Comment("리워드 달성 일자")
    private LocalDate achievedAt;

    @Comment("리워드 획득 일자")
    private LocalDate obtainedAt;

    private MemberObtainReward(Member member, Reward reward,
        LocalDate achievedAt, LocalDate obtainedAt) {
        this.id = new MemberRewardId(member, reward);
        this.achievedAt = achievedAt;
        this.obtainedAt = obtainedAt;
    }

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class MemberRewardId implements Serializable {

        @ManyToOne
        @JoinColumn(name = "member_id")
        private Member member;
        @ManyToOne
        @JoinColumn(name = "reward_id")
        private Reward reward;

        private MemberRewardId(Member member, Reward reward) {
            this.member = member;
            this.reward = reward;
        }

    }


}

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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberObtainReward {

    @EmbeddedId
    private MemberRewardId id;

    @Comment("리워드 달성 여부")
    private boolean isAchieved;

    @Comment("포인트 획득 여부")
    private boolean isObtained;

    @Comment("리워드 달성 일자")
    private DateTime achievedAt;

    private MemberObtainReward(Member member, Reward reward,
        boolean isAchieved, boolean isObtained, DateTime achievedAt) {
        this.id = new MemberRewardId(member, reward);
        this.isAchieved = isAchieved;
        this.isObtained = isObtained;
        this.achievedAt = achievedAt;
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

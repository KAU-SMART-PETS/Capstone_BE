package com.kau.capstone.entity.reward;

import com.kau.capstone.global.common.BaseEntity;
import com.kau.capstone.v2.reward.dto.RewardCreateReqV2;
import com.kau.capstone.v2.reward.dto.RewardUpdateReqV2;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reward extends BaseEntity {

    @Id
    @Comment("리워드 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("리워드 제목")
    private String title;

    @Comment("리워드 세부 내용")
    private String content;

    @Comment("획득 포인트")
    private int earnPoint;

    private Reward(String title, String content, int earnPoint) {
        this.title = title;
        this.content = content;
        this.earnPoint = earnPoint;
    }

    public static Reward of(RewardCreateReqV2 request) {
        return new Reward(
            request.title(),
            request.content(),
            request.earnPoint()
        );
    }

    public void updateReward(RewardUpdateReqV2 request) {
        this.title = request.title();
        this.content = request.content();
    }

    public void deleteReward() {
        this.delete(LocalDateTime.now());
    }
}

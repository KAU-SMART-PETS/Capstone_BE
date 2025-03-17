package com.kau.capstone.entity.reward;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRewardId implements Serializable {

    private Long member;
    private Long reward;

    protected MemberRewardId(Long member, Long reward) {
        this.member = member;
        this.reward = reward;
    }

}

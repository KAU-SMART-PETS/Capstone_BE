package com.kau.capstone.entity.walk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkTime {

    @Comment("산책 시작 시간")
    @Column
    private LocalDateTime startTime;

    @Comment("산책 종료 시간")
    @Column
    private LocalDateTime endTime;

    private WalkTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static WalkTime of(LocalDateTime startTime, LocalDateTime endTime) {
        return new WalkTime(startTime, endTime);
    }

}

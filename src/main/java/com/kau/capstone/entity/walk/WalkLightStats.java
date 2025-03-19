package com.kau.capstone.entity.walk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkLightStats {

    @Comment("누적 조도량")
    @Column
    private Double tLux;

    @Comment("평균 색온도")
    @Column
    private Double avgK;

    @Comment("평균 조도량")
    @Column
    private Double avgLux;

    private WalkLightStats(Double tLux, Double avgK, Double avgLux) {
        this.tLux = tLux;
        this.avgK = avgK;
        this.avgLux = avgLux;
    }

    public static WalkLightStats of(Double tLux, Double avgK, Double avgLux) {
        return new WalkLightStats(tLux, avgK, avgLux);
    }

}

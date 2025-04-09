package com.kau.capstone.entity.point;

import lombok.Getter;

@Getter
public enum PointType {
    PAYMENT_POINT("포인트 결제"),
    EARN_POINT("포인트 적립");

    final String description;
    
    PointType(String description) {
        this.description = description;
    }

    public static PointType getPointType(String description) {
        for (PointType pointType : PointType.values()) {
            if (pointType.description.equals(description)) {
                return pointType;
            }
        }
        return null;
    }
}

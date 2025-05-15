package com.kau.capstone.v2.walk.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record WalkCreateReqV2(
    @NotNull
    LocalDateTime startTime,
    @NotNull
    LocalDateTime endTime,
    @NotNull
    long duration,
    @NotNull
    double distance
) {

}

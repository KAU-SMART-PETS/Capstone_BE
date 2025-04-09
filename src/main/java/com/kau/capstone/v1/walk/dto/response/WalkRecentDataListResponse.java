package com.kau.capstone.v1.walk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class WalkRecentDataListResponse {

    private List<WalkRecentDataResponse> recentWalk;
}

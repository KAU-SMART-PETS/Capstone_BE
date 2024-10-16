package com.kau.capstone.domain.vet.controller;

import com.kau.capstone.domain.vet.dto.MemberLocationRequest;
import com.kau.capstone.domain.vet.dto.VetDetailResponse;
import com.kau.capstone.domain.vet.dto.VetsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "동물병원 API")
public interface VetApi {

    @Operation(summary = "동물병원 세부 정보 조회", description = "동물병원 목록에서 동물병원을 클릭하면 볼 수 있는 세부 정보입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "동물병원 세부 정보 조회 성공",
            content = @Content(schema = @Schema(implementation = VetDetailResponse.class))
    )
    @GetMapping("/api/v1/vets/{vetId}")
    ResponseEntity<VetDetailResponse> getVetDetailInfo(
            @Parameter(description = "동물병원 ID")
            @PathVariable Long vetId,
            @Parameter(description = "현재 사용자의 위치 (동물병원과 사용자 사이의 거리를 구할 때 사용합니다.")
            @RequestBody MemberLocationRequest memberLocationRequest);

    @Operation(summary = "동물병원 목록 조회", description = "동물병원 전체 목록을 조회합니다. (현재는 전체 데이터 반환이지만, 나중에 논의 필요)")
    @ApiResponse(
            responseCode = "200",
            description = "동물병원 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = VetsResponse.class))
    )
    @GetMapping("/api/v1/vets")
    ResponseEntity<VetsResponse> getVetsInfo(
            @Parameter(description = "현재 사용자의 위치 (동물병원과 사용자 사이의 거리를 구할 때 사용합니다.")
            @RequestBody MemberLocationRequest memberLocationRequest
    );
}

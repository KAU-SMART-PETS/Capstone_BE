package com.kau.capstone.domain.member.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.member.dto.MemberInfoResponse;
import com.kau.capstone.domain.member.dto.ModifyMemberRequest;
import com.kau.capstone.domain.member.dto.OwnedPetsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "보건정보 API")
public interface MemberApi {

    @Operation(summary = "사용자 정보 조회", description = "사용자에게 등록된 정보를 보여주는 기능입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "사용자 정보 조회 성공",
            content = @Content(schema = @Schema(implementation = MemberInfoResponse.class))
    )
    @GetMapping("/api/v1/users")
    ResponseEntity<MemberInfoResponse> getMemberInfo(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo
    );

    @Operation(summary = "사용자 정보 수정", description = "사용자의 정보를 수정하는 기능입니다.")
    @ApiResponse(
            responseCode = "204",
            description = "사용자 정보 수정 성공"
    )
    @PatchMapping("/api/v1/users")
    ResponseEntity<Void> putMemberInfo(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo,
            @Parameter(description = "유저 정보 수정 내용 (이메일, 휴대폰 번호, SMS 수신 동의, 이메일 수신 동의")
            @RequestBody ModifyMemberRequest request
    );

    @Operation(summary = "사용자가 등록한 반려동물 목록 조회", description = "사용자가 등록한 반려동물들의 목록을 제공합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "반려동물 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = OwnedPetsResponse.class))
    )
    @GetMapping("/api/v1/users/pets")
    ResponseEntity<OwnedPetsResponse> getOwnerPets(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo
    );
}

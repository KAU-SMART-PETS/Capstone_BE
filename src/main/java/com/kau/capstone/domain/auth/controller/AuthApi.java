package com.kau.capstone.domain.auth.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "소셜 로그인 API")
public interface AuthApi {

    @Operation(summary = "[사용X] 테스트 로그인", description = "테스트용으로 사용하는 로그인입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "로그인 성공"
    )
    @PostMapping("/api/v1/login/{memberId}")
    ResponseEntity<Void> login(
            @Parameter(description = "[사용X] 로그인 정보")
            @PathVariable Long memberId,
            @Parameter(description = "[전송X] 백엔드에서 자동으로 얻는 값입니다.")
            HttpServletRequest request
    );

    @Operation(summary = "[사용X] 테스트 로그아웃", description = "테스트용으로 로그인 했던걸 다시 로그아웃하는 곳입니다.")
    @ApiResponse(
            responseCode = "301",
            description = "로그아웃 성공"
    )
    @PostMapping("/api/v1/logout")
    ResponseEntity<Void> logout(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo,
            @Parameter(description = "[전송X] 백엔드에서 자동으로 얻는 값입니다.")
            HttpServletRequest request, HttpServletResponse response
    );

    @Operation(summary = "소셜 로그인 리다이렉트 URL 조회", description = "소셜 로그인시 필요한 리다이렉트 주소입니다 (각 소셜 로그인 문서 참조)")
    @ApiResponse(
            responseCode = "302",
            description = "URL 획득 성공"
    )
    @GetMapping("/api/v1/oauth2/{site}")
    ResponseEntity<Void> oauthLogin(
            @Parameter(description = "소셜 로그인 사이트 (ex. naver, kakao)")
            @PathVariable String site
    );

    @Operation(summary = "소셜 로그인", description = "소셜 로그인을 하는 곳입니다 (각 소셜 로그인 문서 참조)")
    @ApiResponse(
            responseCode = "301",
            description = "소셜 로그인 성공"
    )
    @PostMapping("/api/v1/oauth2/{site}/code")
    ResponseEntity<Void> oauthLoginCode(
            @Parameter(description = "소셜 로그인 사이트 (ex. naver, kakao")
            @PathVariable String site,
            @Parameter(description = "소셜 로그인시 사용하는 코드 (소셜 로그인 문서, 노션 infos 문서 참조)")
            @RequestParam("code") String code,
            @Parameter(description = "[전송X] 백엔드에서 자동으로 얻는 값입니다.")
            HttpServletRequest request
    );

    @Operation(summary = "소셜 로그인 로그아웃", description = "로그아웃 기능입니다.")
    @ApiResponse(
            responseCode = "301",
            description = "로그아웃 성공"
    )
    @PostMapping("/api/v1/oauth2/logout")
    ResponseEntity<Void> oauthLogout(
            @Parameter(description = "로그인 정보")
            @LoginUser LoginInfo loginInfo,
            @Parameter(description = "[전송X] 백엔드에서 자동으로 얻는 값입니다.")
            HttpServletRequest request,
            @Parameter(description = "[전송X] 백엔드에서 자동으로 얻는 값입니다.")
            HttpServletResponse response
    );
}

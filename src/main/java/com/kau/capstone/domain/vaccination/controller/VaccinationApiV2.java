package com.kau.capstone.domain.vaccination.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.vaccination.dto.CreateVaccinationRequestV2;
import com.kau.capstone.domain.vaccination.dto.PutVaccinationRequestV2;
import com.kau.capstone.domain.vaccination.dto.VaccinationsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "보건정보 API")
public interface VaccinationApiV2 {

    @Operation(summary = "보건정보 등록 v2", description = "반려견의 보건정보를 등록하는 기능입니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "201",
        description = "보건정보 등록 성공"
    )
    @PostMapping("/api/v2/pets/{petId}/vaccination")
    ResponseEntity<ApiResponse<Void>> createVaccinationInfoForPet(
        @Parameter(description = "로그인 정보") @LoginUser LoginInfo loginInfo,
        @Parameter(description = "반려동물 ID") @PathVariable Long petId,
        @Parameter(description = "보건정보 관련 이름, 접종시기") @RequestBody CreateVaccinationRequestV2 request
    );

//    @Operation(summary = "반려동물 보건정보 목록 조회", description = "반려견에게 등록된 보건정보 목록입니다.")
//    @ApiResponse(
//            responseCode = "200",
//            description = "보건정보 목록 조회 성공",
//            content = @Content(schema = @Schema(implementation = VaccinationsResponse.class))
//    )
//    @GetMapping("/api/v1/pets/{petId}/vaccination")
//    ResponseEntity<VaccinationsResponse> getVaccinationInfoForPet(
//            @Parameter(description = "반려동물 ID")
//            @PathVariable Long petId
//    );
//
    @Operation(summary = "보건정보 수정 v2", description = "반려견에게 등록된 보건정보를 수정하는 기능입니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "보건정보 수정 성공"
    )
    @PutMapping("/api/v2/pets/{petId}/vaccination/{vaccinationId}")
    ResponseEntity<ApiResponse<Void>> putVaccinationInfoForPet(
        @Parameter(description = "로그인 정보") @LoginUser LoginInfo loginInfo,
        @Parameter(description = "반려동물 ID") @PathVariable Long petId,
        @Parameter(description = "보건정보 ID") @PathVariable Long vaccinationId,
        @Parameter(description = "수정할 보건정보 관련 이름, 접종시기") @RequestBody PutVaccinationRequestV2 request
    );

    @Operation(summary = "보건정보 삭제 v2", description = "반려견에게 등록된 보건정보를 삭제하는 기능입니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "보건정보 삭제 성공"
    )
    @DeleteMapping("/api/v2/pets/{petId}/vaccination/{vaccinationId}")
    ResponseEntity<ApiResponse<Void>> deleteVaccinationInfoForPet(
        @Parameter(description = "로그인 정보") @LoginUser LoginInfo loginInfo,
        @Parameter(description = "반려동물 ID") @PathVariable Long petId,
        @Parameter(description = "보건정보 ID") @PathVariable Long vaccinationId
    );

}

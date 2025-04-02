package com.kau.capstone.v2.ai.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v2.ai.dto.request.EyeReqV2;
import com.kau.capstone.v2.ai.service.AIServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v2/ai")
@RequiredArgsConstructor
public class AiRestControllerV2 {

    private final AIServiceV2 aiService;

    @PostMapping("/eye/{petId}")
    public ResponseEntity<ApiResponse<EyeReqV2>> analyzeEye(
        @LoginUser LoginInfo loginInfo,
        @PathVariable Long petId,
        @RequestPart("AnimalImage") MultipartFile image
    ) {
        EyeReqV2 eyeProbs = aiService.analyzeEye(loginInfo, petId, image);
        return ApiResponse.ok(eyeProbs);
    }


}

package com.kau.capstone.domain.ai.controller;


import com.kau.capstone.domain.ai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;





@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AIController {

    private final AIService aiService;

    // 강아지, 고양이 질병 분류
    @PostMapping("/eye")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> analyzePetImage(@RequestPart("AnimalImage") MultipartFile image,
                                                  @RequestParam("PetType") String petType) {
        // AI 분석 서비스 호출
        Map<String, Object> aiResponse = aiService.analyzePetImage(image, petType);

        return ResponseEntity.ok(aiResponse);
    }


}


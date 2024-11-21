package com.kau.capstone.domain.ai.controller;


import com.kau.capstone.domain.ai.service.AIService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
public class AIController {

    private final AIService aiService;

    // 강아지, 고양이 질병 분류
    @PostMapping("/eye")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> analyzePetImage(
        @RequestPart("AnimalImage") MultipartFile image,
        @RequestParam("PetType") String petType) {
        // AI 분석 서비스 호출
        Map<String, Object> aiResponse = aiService.analyzePetImage(image, petType);

        return ResponseEntity.ok(aiResponse);
    }

    //반려동물 비문 등록
    @PostMapping("/nose/regist")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> registPetNose(
        @RequestPart("animalImage") MultipartFile image,
        @RequestParam("petId") Long petId
    ) {
        String response = aiService.registPetNose(image, petId);
        return ResponseEntity.ok(response);
    }


}


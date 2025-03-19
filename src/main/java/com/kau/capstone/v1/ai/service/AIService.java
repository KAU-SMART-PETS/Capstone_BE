package com.kau.capstone.v1.ai.service;


import com.kau.capstone.global.aiModel.AIModelClient;
import com.kau.capstone.global.common.s3.FileService;
import com.kau.capstone.v1.ai.AIImage;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIService {

    private final AIModelClient aiModelClient;
    private final FileService fileService;

    // 이미지를 저장하고 AI 모델에 요청을 보내는 로직
    public Map<String, Object> analyzePetImage(MultipartFile imageFile, String petType) {
        // S3에 이미지 저장
        String imageUrl = fileService.uploadImage(imageFile, "ai/eyes/");
        AIImage savedImage = new AIImage(imageFile.getOriginalFilename(), imageUrl);

        // AI 모델 서버로 이미지 URL 전송
        Map<String, Object> aiResponse = aiModelClient.analyzeImage(savedImage.getUrl(), petType);

        // S3에서 이미지 삭제
        fileService.deleteImage(savedImage.getUrl());

        return aiResponse;
    }

    public String registPetNose(MultipartFile image, Long petId) {
        // S3에 이미지 저장
        String imageUrl = fileService.uploadImage(image, "ai/nose/train");
        AIImage savedImage = new AIImage(image.getOriginalFilename(), imageUrl);

        String response = aiModelClient.registNoseImage(savedImage.getUrl(), petId);

        // S3에서 이미지 삭제
        fileService.deleteImage(savedImage.getUrl());

        return response;
    }

    public Map<String, Object> testPetNose(MultipartFile image) {
        String imageUrl = fileService.uploadImage(image, "ai/nose/test");
        AIImage savedImage = new AIImage(image.getOriginalFilename(), imageUrl);

        Map<String, Object> response = aiModelClient.testNoseImage(savedImage.getUrl());
        fileService.deleteImage(savedImage.getUrl());
        return response;
    }
}

package com.kau.capstone.domain.ai.service;



import com.kau.capstone.domain.ai.AIImage;
import com.kau.capstone.domain.ai.client.AIModelClient;
import com.kau.capstone.domain.ai.infra.S3StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIService {

    private final S3StorageService s3StorageService;
    private final AIModelClient aiModelClient;

    // 이미지를 저장하고 AI 모델에 요청을 보내는 로직
    public Map<String, Object> analyzePetImage(MultipartFile imageFile, String petType) {
        // S3에 이미지 저장
        AIImage savedImage = s3StorageService.uploadImage(imageFile);

        // AI 모델 서버로 이미지 URL 전송
        Map<String, Object> aiResponse = aiModelClient.analyzeImage(savedImage.getUrl(), petType);

        // S3에서 이미지 삭제
        s3StorageService.deleteImage(savedImage.getUrl());

        return aiResponse;
    }
}

package com.kau.capstone.domain.ai.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.kau.capstone.domain.ai.dto.ImageSaveDto;
import com.kau.capstone.domain.ai.entity.Image;
import com.kau.capstone.domain.ai.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static String bucketName = "ai-capstone-bucket";

    private final AmazonS3Client amazonS3Client;
    private final ImageRepository imageRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public List<String> saveImages(ImageSaveDto saveDto) {
        List<String> resultList = new ArrayList<>();

        for(MultipartFile multipartFile : saveDto.getImages()) {
            String value = saveImage(multipartFile);
            resultList.add(value);
        }

        return resultList;
    }

    @Transactional
    public String saveImage(MultipartFile multipartFile) {
        String originalName = multipartFile.getOriginalFilename();
        Image image = new Image(originalName);
        String filename = image.getStoredName();

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, filename, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, filename).toString();
            image.setAccessUrl(accessUrl);
        } catch(IOException e) {
            throw new RuntimeException("S3 업로드 실패", e);
        }

        imageRepository.save(image);

        return image.getAccessUrl();
    }

    public Object sendImagesToAIModel(List<String> imageUrls) {
        // AI 모델 서버의 URL
        String aiModelUrl = "http://ai-model-server-url/endpoint";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<List<String>> requestEntity = new HttpEntity<>(imageUrls, headers);

        // AI-Model 서버로 http(Json) 요청 보내기
        // TODO : Request Param 으로 수정 여부 체크하기
        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    aiModelUrl,
                    HttpMethod.POST,
                    requestEntity,
                    // TODO : 반환 타입 결정되면 구체적으로 결정
                    Object.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("AI 모델 서버 통신 실패", e);
        }

    }
}

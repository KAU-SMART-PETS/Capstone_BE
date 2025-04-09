package com.kau.capstone.v1.ai.infra;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.kau.capstone.v1.ai.AIImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3StorageService {

    private static final String bucketName = "server-capstone-bucket";
    private final AmazonS3Client amazonS3Client;

    public AIImage uploadImage(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String S3Path = "ai/" + fileName;
        String accessUrl;

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, S3Path, multipartFile.getInputStream(), objectMetadata);
            accessUrl = "s3://" + bucketName + "/" + S3Path;
        } catch (IOException e) {
            throw new RuntimeException("S3 업로드 실패", e);
        }

        return new AIImage(fileName, accessUrl);  // 이미지 객체를 반환
    }

    public void deleteImage(String imageUrl) {
        try {
            String S3Path = imageUrl.substring(imageUrl.indexOf("ai"));
            amazonS3Client.deleteObject(bucketName, S3Path);
            log.info("이미지 삭제 완료: {}", S3Path);
        } catch (Exception e) {
            throw new RuntimeException("S3 이미지 삭제 실패", e);
        }
    }
}

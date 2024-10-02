package com.kau.capstone.global.common.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kau.capstone.domain.pet.entity.Pet;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    private final String bucket;

    public S3Service(AmazonS3 amazonS3, @Value("${cloud.aws.s3.bucket}") String bucket) {
        this.amazonS3 = amazonS3;
        this.bucket = bucket;
    }

    public String upload(MultipartFile multipartFile, String dirName, String fileName)
        throws IOException {
        String fileRoute = dirName + "/" + fileName;
        log.info("fileName: " + fileRoute);

        String contentType = multipartFile.getContentType();
        InputStream inputStream = multipartFile.getInputStream();
        String uploadImageUrl = putS3(fileRoute, contentType, inputStream);

        return uploadImageUrl;
    }

    private String putS3(String fileRoute, String contentType, InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);  // MIME 타입 지정

        amazonS3.putObject(new PutObjectRequest(bucket, fileRoute, inputStream, metadata)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, fileRoute).toString();
    }

    public void delete(Pet pet) {
        String key = pet.getImageUrl().split("amazonaws.com/")[1];
        log.info(key);

        if (amazonS3.doesObjectExist(bucket, key)) {
            amazonS3.deleteObject(bucket, key);
        }
    }

}
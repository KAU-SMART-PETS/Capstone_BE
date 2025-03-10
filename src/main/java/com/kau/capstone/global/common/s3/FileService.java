package com.kau.capstone.global.common.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kau.capstone.global.common.s3.exception.FileIOException;
import com.kau.capstone.global.common.s3.exception.FileNotExistException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final AmazonS3 amazonS3;

    private final String bucket;

    private FileService(AmazonS3 amazonS3, @Value("${cloud.aws.s3.bucket}") String bucket) {
        this.amazonS3 = amazonS3;
        this.bucket = bucket;
    }

    public String uploadImage(MultipartFile file, String dirName) {
        String imageUrl;
        try {
            String fileRoute = dirName + file.getName();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();
            imageUrl = putS3(fileRoute, contentType, inputStream);
        } catch (IOException e) {
            throw new FileIOException();
        }

        return imageUrl;
    }

    public void deleteImage(String imageUrl) {
        String key = imageUrl.split("amazonaws.com/")[1];
        try {
            delete(key);
        } catch (FileNotExistException e) {
            throw new FileNotExistException();
        }
    }

    private String putS3(String fileRoute, String contentType, InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);  // MIME 타입 지정

        amazonS3.putObject(new PutObjectRequest(bucket, fileRoute, inputStream, metadata)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, fileRoute).toString();
    }

    private void delete(String key) {
        if (amazonS3.doesObjectExist(bucket, key)) {
            amazonS3.deleteObject(bucket, key);
        } else {
            throw new FileNotExistException();
        }
    }

}

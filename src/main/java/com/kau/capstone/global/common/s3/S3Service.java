package com.kau.capstone.global.common.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kau.capstone.global.common.s3.exception.CannotConvertFileException;
import com.kau.capstone.global.exception.ErrorCode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

        File uploadFile = convert(multipartFile, fileName);
        String uploadImageUrl = putS3(uploadFile, fileRoute);
        uploadFile.delete();
        return uploadImageUrl;
    }

    private File convert(MultipartFile file, String fileName) throws IOException {
        File convertFile = new File(fileName);

        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            } catch (IOException e) {
                log.error("파일 변환 중 오류 발생: {}", e.getMessage());
                throw new CannotConvertFileException(ErrorCode.CANNOT_CONVERT_FILE);
            }
            return convertFile;
        }

        throw new CannotConvertFileException(ErrorCode.CANNOT_CONVERT_FILE);
    }

    private String putS3(File uploadFile, String fileRoute) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileRoute, uploadFile)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, fileRoute).toString();
    }

}
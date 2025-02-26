package com.kau.capstone.global.common.s3;

import com.kau.capstone.global.common.s3.exception.FileIOException;
import com.kau.capstone.global.common.s3.exception.FileNotExistException;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final S3Service s3Service;

    public String uploadImage(MultipartFile file, String dirName) {
        String imageUrl;
        try {
            String fileRoute = dirName + file.getName();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();
            imageUrl = s3Service.putS3(fileRoute, contentType, inputStream);
        } catch (IOException e) {
            throw new FileIOException();
        }

        return imageUrl;
    }

    public void deleteImage(String imageUrl) {
        String key = imageUrl.split("amazonaws.com/")[1];
        try {
            s3Service.delete(key);
        } catch (FileNotExistException e) {
            throw new FileNotExistException();
        }
    }

}

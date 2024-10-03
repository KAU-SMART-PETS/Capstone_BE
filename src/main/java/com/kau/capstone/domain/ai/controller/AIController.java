package com.kau.capstone.domain.ai.controller;

import com.kau.capstone.domain.ai.dto.ImageSaveDto;
import com.kau.capstone.domain.ai.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AIController {

    private final ImageService imageService;

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> saveImage(@ModelAttribute ImageSaveDto imageSaveDto) {
        List<String> imageUrls = imageService.saveImages(imageSaveDto);
        // TODO : 추후에 타입 결정해야한다
        Object aiResponse = imageService.sendImagesToAIModel(imageUrls);

        return new ResponseEntity<>(aiResponse, HttpStatus.OK);
    }

}

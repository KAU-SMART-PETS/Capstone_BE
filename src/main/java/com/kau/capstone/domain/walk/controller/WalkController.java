package com.kau.capstone.domain.walk.controller;

import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.WalkResponse;
import com.kau.capstone.domain.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    @PostMapping("/end")
    public ResponseEntity<WalkResponse> endWalk(@RequestBody WalkRequest walkData){
        WalkResponse response = walkService.saveWalkData(walkData);
        return ResponseEntity.ok(response);
    }
}

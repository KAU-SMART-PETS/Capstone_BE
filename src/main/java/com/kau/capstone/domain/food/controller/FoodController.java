package com.kau.capstone.domain.food.controller;

import com.kau.capstone.domain.food.dto.FoodsResponse;
import com.kau.capstone.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/api/v1/foods")
    public ResponseEntity<FoodsResponse> getFoodsInfo() {
        FoodsResponse response = foodService.getFoodsInfo();

        return ResponseEntity.ok(response);
    }
}

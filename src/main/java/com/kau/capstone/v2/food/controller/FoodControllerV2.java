package com.kau.capstone.v2.food.controller;

import com.kau.capstone._core.dto.ApiResponse;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v1.point.dto.DeliveryFeeRequest;
import com.kau.capstone.v1.point.service.PointService;
import com.kau.capstone.v2.food.dto.response.FoodsResponseV2;
import com.kau.capstone.v2.food.service.FoodServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FoodControllerV2 {
    private final FoodServiceV2 foodServiceV2;
    private final PointService pointService;

    @GetMapping("/api/v2/foods")
    public ResponseEntity<ApiResponse<FoodsResponseV2>> getFoodsInfo() {
        FoodsResponseV2 response = foodServiceV2.getFoodsInfo();

        return ApiResponse.ok(response);
    }

    @PostMapping("/api/v2/foods/{foodId}/points/payment")
    public ResponseEntity<ApiResponse<Void>> payFoodWithPoints(@LoginUser LoginInfo loginInfo,
                                                  @PathVariable Long foodId,
                                                  @RequestBody DeliveryFeeRequest request) {
        foodServiceV2.payFoodWithPoints(loginInfo.memberId(), foodId, request.deliveryFee());

        return ApiResponse.ok();
    }
}

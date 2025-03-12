package com.kau.capstone.v1.food.controller;

import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v1.food.dto.FoodsResponse;
import com.kau.capstone.v1.food.service.FoodService;
import com.kau.capstone.v1.point.dto.DeliveryFeeRequest;
import com.kau.capstone.v1.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FoodController implements FoodApi {

    private final FoodService foodService;
    private final PointService pointService;

    @GetMapping("/api/v1/foods")
    public ResponseEntity<FoodsResponse> getFoodsInfo() {
        FoodsResponse response = foodService.getFoodsInfo();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/v1/foods/{foodId}/points/payment")
    public ResponseEntity<Void> payFoodWithPoints(@LoginUser LoginInfo loginInfo,
                                                  @PathVariable Long foodId,
                                                  @RequestBody DeliveryFeeRequest request) {
        pointService.processPointPaymentForFood(loginInfo.memberId(), foodId, request.deliveryFee());

        return ResponseEntity.ok().build();
    }
}

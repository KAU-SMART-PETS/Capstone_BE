package com.kau.capstone.v2.food.dto.response;

import com.kau.capstone.entity.food.Food;

import java.util.List;

public record FoodsResponseV2(
        List<FoodResponseV2> foods
) {

    public static FoodsResponseV2 of(List<Food> foods) {
        List<FoodResponseV2> foodResponses = foods.stream()
                .map(FoodResponseV2::of)
                .toList();

        return new FoodsResponseV2(foodResponses);
    }
}

package com.kau.capstone.domain.food.service;

import com.kau.capstone.domain.food.dto.FoodsResponse;
import com.kau.capstone.domain.food.entity.Food;
import com.kau.capstone.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodsResponse getFoodsInfo() {
        List<Food> foods = foodRepository.findAll();

        return FoodsResponse.toResponse(foods);
    }
}

package com.kau.capstone.initial;

import com.kau.capstone.entity.food.Food;
import com.kau.capstone.entity.food.repository.FoodRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class FoodInitializer {

    private final FoodRepository foodRepository;

//    @PostConstruct
//    public void init() {
//        // 사료 이미지 추가
//        var food1 = create("시저 성견용 연어맛이 곁들여진 쇠고기 건식 사료", 8500L, "https://thumbnail10.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/2256824262381067-6cbaab6b-0381-4ac1-bb4d-e3cde73a8020.jpg");
//        var food2 = create("지니펫 유기농&활기찬일상 강아지사료 항산화 건식사료,  닭", 22000L, "https://thumbnail10.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1066056294989691-10dab58a-4aea-4dcb-9a3f-c082d31b0d7f.jpg");
//        var food3 = create("무마진 강아지 전연령용 소프트사료, 연어", 14900L, "https://thumbnail10.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/2791828730297325-4999e7fd-60dd-4c19-89c5-6e39a985641d.png");
//        var food4 = create("버틀러 3개월령 이상 더텐 소프트 밀웜 소프트사료, 오리", 21000L, "https://thumbnail10.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/2268100251879690-7d94cf09-07d9-4cf7-b8c8-4951bdecc6e4.png");
//        var food5 = create("소프트플러스 전연령 참 좋은 황태 강아지 소프트사료, 연어", 11700L, "https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/b119/c157e5ee1c9140c28de31446550a9eb84c84ac3125a011f5631a17f500b0.jpg");
//        var food6 = create("마이펫닥터 전연령 시그니처 티어스컨트롤 유기농 강아지사료, 눈물 개선/눈건강", 30000L, "https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1072637633682648-fadc9cb3-f91f-41c9-99e7-ae611099246c.jpg");
//
//        save(food1, food2, food3, food4, food5, food6);
//    }
//
//    private Food create(String name, Long price, String imageUrl) {
//        return Food.builder()
//                .name(name)
//                .price(price)
//                .imageUrl(imageUrl)
//                .build();
//    }
//
//    private void save(Food... foods) {
//        foodRepository.saveAll(Arrays.stream(foods).toList());
//    }
}

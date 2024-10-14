package com.kau.capstone.domain.food.controller;

import com.kau.capstone.domain.food.dto.FoodsResponse;
import com.kau.capstone.domain.food.entity.Food;
import com.kau.capstone.global.common.ControllerTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FoodControllerTest extends ControllerTest {

    @Nested
    class getFoodsInfo_성공_테스트 {

        @Test
        void 사료_목록을_확인할_수_있다() {
            // given
            Food food1 = Food.builder()
                    .name("사료 1")
                    .price(5000L)
                    .imageUrl("www.test1.com")
                    .build();
            Food food2 = Food.builder()
                    .name("사료 2")
                    .price(10000L)
                    .imageUrl("www.test2.com")
                    .build();
            foodRepository.saveAll(List.of(food1, food2));

            // when
            ExtractableResponse<Response> res = RestAssured.given()
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/foods")
                    .then()
                    .extract();
            FoodsResponse response = res.jsonPath().getObject("", FoodsResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(response.foods().size()).isEqualTo(2);
                soft.assertThat(food1.getId()).isEqualTo(response.foods().get(0).id());
                soft.assertThat(food2.getId()).isEqualTo(response.foods().get(1).id());
            });
        }
    }
}

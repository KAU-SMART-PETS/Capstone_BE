package com.kau.capstone.domain.reward.controller;

import com.kau.capstone.domain.food.entity.Food;
import com.kau.capstone.domain.point.dto.DeliveryFeeRequest;
import com.kau.capstone.domain.reward.dto.RewardsResponse;
import com.kau.capstone.global.common.ControllerTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RewardControllerTest extends ControllerTest {

    @Nested
    class getRewardsInfo_성공_테스트 {

        @Test
        void 사용자는_리워드_목록을_조회할_수_있다() {
            // given
            String cookie = getCookie("1");

            // when
            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/rewards")
                    .then()
                    .extract();
            RewardsResponse response = res.jsonPath().getObject("", RewardsResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
                soft.assertThat(response.rewards().size()).isEqualTo(5);
            });
        }

        @Test
        void 사료_구매시_리워드_성공을_확인할_수_있다() {
            // given
            String cookie = getCookie("1");

            Food food = Food.builder()
                    .name("test food")
                    .price(0L)
                    .build();
            foodRepository.save(food);

            DeliveryFeeRequest request = new DeliveryFeeRequest(0L);
            RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .post("/api/v1/foods/1/points/payment")
                    .then()
                    .extract();

            // when
            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/rewards")
                    .then()
                    .extract();
            RewardsResponse response = res.jsonPath().getObject("", RewardsResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
                soft.assertThat(response.rewards().size()).isEqualTo(5);
                soft.assertThat(response.rewards().get(2).isAchieved()).isTrue();
            });
        }
    }
}

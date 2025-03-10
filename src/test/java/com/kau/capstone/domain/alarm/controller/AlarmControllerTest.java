package com.kau.capstone.domain.alarm.controller;

import com.kau.capstone.v1.alarm.dto.AlarmResponse;
import com.kau.capstone.entity.food.Food;
import com.kau.capstone.v1.point.dto.DeliveryFeeRequest;
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
class AlarmControllerTest extends ControllerTest {

    @Nested
    class getAlarmInfo_성공_테스트 {

        @Test
        void 사용자는_알람_목록을_볼_수_있다() {
            // given
            String cookie = getCookie("1");

            // when
            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/alarm")
                    .then()
                    .extract();
            AlarmResponse response = res.jsonPath().getObject("", AlarmResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
                soft.assertThat(response.alarm().size()).isEqualTo(5);
            });
        }

        @Test
        void 사료_구매시_알람_목록이_보이지_않아야_한다() {
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
                    .get("/api/v1/alarm")
                    .then()
                    .extract();
            AlarmResponse response = res.jsonPath().getObject("", AlarmResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
                soft.assertThat(response.alarm().size()).isEqualTo(4);
            });
        }
    }
}

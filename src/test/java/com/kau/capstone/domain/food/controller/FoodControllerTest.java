//package com.kau.capstone.domain.food.controller;
//
//import com.kau.capstone.v1.food.dto.FoodsResponseV2;
//import com.kau.capstone.entity.food.Food;
//import com.kau.capstone.entity.member.Member;
//import com.kau.capstone.v1.point.dto.DeliveryFeeRequest;
//import com.kau.capstone.entity.point.Point;
//import com.kau.capstone.global.common.ControllerTest;
//import com.kau.capstone.global.common.ResponseDTO;
//import com.kau.capstone.global.exception.ErrorCode;
//import io.restassured.RestAssured;
//import io.restassured.response.ExtractableResponse;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.DisplayNameGeneration;
//import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.SoftAssertions.*;
//
//@SuppressWarnings("NonAsciiCharacters")
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//class FoodControllerTest extends ControllerTest {

//    @Nested
//    class getFoodsInfo_성공_테스트 {
//
//        @Test
//        void 사료_목록을_확인할_수_있다() {
//            // given
//            Food food1 = Food.builder()
//                    .name("사료 1")
//                    .price(5000L)
//                    .imageUrl("www.test1.com")
//                    .build();
//            Food food2 = Food.builder()
//                    .name("사료 2")
//                    .price(10000L)
//                    .imageUrl("www.test2.com")
//                    .build();
//            foodRepository.saveAll(List.of(food1, food2));
//
//            // when
//            ExtractableResponse<Response> res = RestAssured.given()
//                    .contentType("application/json")
//                    .when()
//                    .get("/api/v1/foods")
//                    .then()
//                    .extract();
//            FoodsResponseV2 response = res.jsonPath().getObject("", FoodsResponseV2.class);
//
//            // then
//            assertSoftly(soft -> {
//                soft.assertThat(response.foods().size()).isEqualTo(2);
//                soft.assertThat(food1.getId()).isEqualTo(response.foods().get(0).id());
//                soft.assertThat(food2.getId()).isEqualTo(response.foods().get(1).id());
//            });
//        }
//    }
//
//    @Nested
//    class payFoodWithPoints_성공_테스트 {
//
//        @Test
//        void 사료_결제시_포인트가_차감되어야_한다() {
//            // given
//
//            Member member = Member.builder()
//                    .name("test")
//                    .platformId("1")
//                    .build();
//            memberRepository.save(member);
//
//            Point point = Point.of(member);
//            pointRepository.save(point);
//
//            member.connectPoint(point);
//
//            Food food = Food.builder()
//                    .name("test food")
//                    .price(2000L)
//                    .build();
//            foodRepository.save(food);
//
//            DeliveryFeeRequest request = new DeliveryFeeRequest(2500L);
//
//            // when
//            String cookie = getCookie("1");
//
//            ExtractableResponse<Response> res = RestAssured.given()
//                    .cookie("JSESSIONID", cookie)
//                    .contentType("application/json")
//                    .body(request)
//                    .when()
//                    .post("/api/v1/foods/1/points/payment")
//                    .then()
//                    .extract();
//
//            // then
//            assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
//        }
//    }
//
//    @Nested
//    class payFoodWithPoints_실패_테스트 {
//
//        @Test
//        void 사료_결제할_포인트가_부족하면_에러가_나와야_한다() {
//            // given
//
//            Member member = Member.builder()
//                    .name("test")
//                    .platformId("1")
//                    .build();
//            memberRepository.save(member);
//
//            Point point = Point.of(member);
//            pointRepository.save(point);
//
//            member.connectPoint(point);
//
//            Food food = Food.builder()
//                    .name("test food")
//                    .price(2000L)
//                    .build();
//            foodRepository.save(food);
//
//            DeliveryFeeRequest request = new DeliveryFeeRequest(2500L);
//
//            // when
//            String cookie = getCookie("1");
//
//            ExtractableResponse<Response> res = RestAssured.given()
//                    .cookie("JSESSIONID", cookie)
//                    .contentType("application/json")
//                    .body(request)
//                    .when()
//                    .post("/api/v1/foods/1/points/payment")
//                    .then()
//                    .extract();
//
//            // then
//            ResponseDTO response = res.jsonPath().getObject("", ResponseDTO.class);
//
//            // then
//            assertSoftly(soft -> {
//                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
//                soft.assertThat(response.getMessage()).isEqualTo(ErrorCode.POINT_NOT_ENOUGH.getSimpleMessage());
//            });
//        }
//    }
//}

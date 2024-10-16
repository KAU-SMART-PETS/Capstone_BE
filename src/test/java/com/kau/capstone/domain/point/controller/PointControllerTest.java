package com.kau.capstone.domain.point.controller;

import com.kau.capstone.domain.food.entity.Food;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.point.dto.DeliveryFeeRequest;
import com.kau.capstone.domain.point.dto.EarnPointRequest;
import com.kau.capstone.domain.point.dto.HistoryResponse;
import com.kau.capstone.domain.point.dto.PayPointRequest;
import com.kau.capstone.domain.point.entity.Point;
import com.kau.capstone.global.common.ControllerTest;
import com.kau.capstone.global.common.ResponseDTO;
import com.kau.capstone.global.exception.ErrorCode;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PointControllerTest extends ControllerTest {


    @Nested
    class payWithPoints_성공_테스트 {

        @Test
        void 유저가_가진_포인트로_결제를_할_수_있다() {
            // given
            Point point = Point.builder()
                    .amount(5000L)
                    .build();
            pointRepository.save(point);

            Member member = Member.builder()
                    .name("test")
                    .point(point)
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            point.connectMember(member);

            PayPointRequest request = new PayPointRequest(5000L);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .patch("/api/v1/points/payment")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }

    @Nested
    class payWithPoints_실패_테스트 {

        @Test
        void 유저가_가진_포인트로_결제할_수_없으면_예외를_반환한다() {
            // given
            Point point = Point.builder()
                    .amount(0L)
                    .build();
            pointRepository.save(point);

            Member member = Member.builder()
                    .name("test")
                    .point(point)
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            point.connectMember(member);

            PayPointRequest request = new PayPointRequest(5000L);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .patch("/api/v1/points/payment")
                    .then()
                    .extract();
            ResponseDTO response = res.jsonPath().getObject("", ResponseDTO.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
                soft.assertThat(response.getMessage()).isEqualTo(ErrorCode.POINT_NOT_ENOUGH.getSimpleMessage());
            });
        }
    }

    @Nested
    class depositWithPoints_성공_테스트 {

        @Test
        void 유저가_포인트를_획득할_수_있다() {
            // given
            Point point = Point.builder()
                    .amount(5000L)
                    .build();
            pointRepository.save(point);

            Member member = Member.builder()
                    .name("test")
                    .point(point)
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            point.connectMember(member);

            EarnPointRequest request = new EarnPointRequest(5000L);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .patch("/api/v1/points/deposit")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }

    @Nested
    class getPointHistory_성공_테스트 {

        @Test
        void 사용자는_자신의_포인트_내역을_볼_수_있다() {
            // given - 초기 데이터 추가
            Point point = Point.builder()
                    .amount(5000L)
                    .build();
            pointRepository.save(point);

            Member member = Member.builder()
                    .name("test")
                    .point(point)
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            point.connectMember(member);

            // given - 사용자가 포인트 적립을 진행
            String cookie = getCookie("1");
            EarnPointRequest request1 = new EarnPointRequest(2000L);
            PayPointRequest request2 = new PayPointRequest(3000L);

            ExtractableResponse<Response> earn1 = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request1)
                    .when()
                    .patch("/api/v1/points/deposit")
                    .then()
                    .extract();
            ExtractableResponse<Response> earn2 = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request2)
                    .when()
                    .patch("/api/v1/points/payment")
                    .then()
                    .extract();

            // when
            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/points")
                    .then()
                    .extract();
            HistoryResponse response = res.jsonPath().getObject("", HistoryResponse.class);

            // then
            assertSoftly(soft -> {
                assertThat(response.history().size()).isEqualTo(2);
                assertThat(request1.point()).isEqualTo(response.history().get(0).changePoint());
                assertThat(request2.point()).isEqualTo(-response.history().get(1).changePoint());
            });
        }

        @Test
        void 사용자_A는_사용자_B의_포인트_내역을_보지_못해야_한다() {
            // given - 사용자1 데이터 추가
            Point point1 = Point.builder()
                    .amount(5000L)
                    .build();
            pointRepository.save(point1);

            Member member1 = Member.builder()
                    .name("test")
                    .point(point1)
                    .platformId("1")
                    .build();
            memberRepository.save(member1);

            point1.connectMember(member1);

            // given - 사용자2 데이터 추가
            Point point2 = Point.builder()
                    .amount(5000L)
                    .build();
            pointRepository.save(point2);

            Member member2 = Member.builder()
                    .name("test")
                    .point(point2)
                    .platformId("2")
                    .build();
            memberRepository.save(member2);

            point2.connectMember(member2);

            // given - 돈 적립
            String cookie1 = getCookie("1");
            EarnPointRequest request1 = new EarnPointRequest(2000L);

            ExtractableResponse<Response> earn1 = RestAssured.given()
                    .cookie("JSESSIONID", cookie1)
                    .contentType("application/json")
                    .body(request1)
                    .when()
                    .patch("/api/v1/points/deposit")
                    .then()
                    .extract();

            String cookie2 = getCookie("2");
            EarnPointRequest request2 = new EarnPointRequest(4000L);

            ExtractableResponse<Response> earn2 = RestAssured.given()
                    .cookie("JSESSIONID", cookie2)
                    .contentType("application/json")
                    .body(request2)
                    .when()
                    .patch("/api/v1/points/deposit")
                    .then()
                    .extract();

            // when
            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie1)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/points")
                    .then()
                    .extract();
            HistoryResponse response = res.jsonPath().getObject("", HistoryResponse.class);

            // then
            assertSoftly(soft -> {
                assertThat(response.history().size()).isEqualTo(1);
                assertThat(request1.point()).isEqualTo(response.history().get(0).changePoint());
            });
        }

        @Test
        void 사용자가_사료_주문시_사료_결제_내역을_볼_수_있다() {
            // given - 결제 전 세팅
            Point point = Point.builder()
                    .amount(10000L)
                    .build();
            pointRepository.save(point);

            Member member = Member.builder()
                    .name("test")
                    .point(point)
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            point.connectMember(member);

            Food food = Food.builder()
                    .name("test food")
                    .price(2000L)
                    .build();
            foodRepository.save(food);

            DeliveryFeeRequest request = new DeliveryFeeRequest(2500L);

            // given - 결제
            String cookie = getCookie("1");

            ExtractableResponse<Response> res1 = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .post("/api/v1/foods/1/points/payment")
                    .then()
                    .extract();

            // when
            ExtractableResponse<Response> res2 = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/points")
                    .then()
                    .extract();
            HistoryResponse response = res2.jsonPath().getObject("", HistoryResponse.class);

            // then
            assertSoftly(soft -> {
                assertThat(response.history().size()).isEqualTo(1);
                assertThat(food.getName()).isEqualTo(response.history().get(0).name());
                assertThat(food.getPrice() + request.deliveryFee()).isEqualTo(-response.history().get(0).changePoint());
            });
        }
    }
}

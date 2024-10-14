package com.kau.capstone.domain.point.controller;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.point.dto.EarnPointRequest;
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
}

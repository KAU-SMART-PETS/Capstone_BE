package com.kau.capstone.domain.member.controller;

import com.kau.capstone.domain.member.dto.MemberInfoResponse;
import com.kau.capstone.domain.member.dto.ModifyMemberRequest;
import com.kau.capstone.domain.member.dto.PayPointRequest;
import com.kau.capstone.domain.member.entity.Member;
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
class MemberControllerTest extends ControllerTest {

    @Nested
    class getMemberInfo_성공_테스트 {

        @Test
        void 유저_정보를_조회한다() {
            // given
            Member saveMember = Member.builder()
                    .socialSite("test")
                    .platformId("1")
                    .name("test")
                    .email("test@test.com")
                    .point(100L)
                    .build();
            Member member = memberRepository.save(saveMember);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/users")
                    .then()
                    .extract();
            MemberInfoResponse response = res.jsonPath().getObject("", MemberInfoResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
                soft.assertThat(member.getName()).isEqualTo(response.name());
                soft.assertThat(member.getEmail()).isEqualTo(response.email());
                soft.assertThat(member.getPhoneNumber()).isEqualTo(response.phoneNumber());
                soft.assertThat(member.getSmsOptIn()).isEqualTo(response.smsOptIn());
                soft.assertThat(member.getEmailOptIn()).isEqualTo(response.emailOptIn());

            });
        }
    }

    @Nested
    class putMemberInfo_성공_테스트 {

        @Test
        void 유저_정보를_수정할_수_있다() {
            // given
            ModifyMemberRequest request = new ModifyMemberRequest("update@update.com", "010-1234-1234", false, false);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .patch("/api/v1/users")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        }
    }

    @Nested
    class payWithPoints_성공_테스트 {

        @Test
        void 유저가_가진_포인트로_결제를_할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .point(10000L)
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            PayPointRequest request = new PayPointRequest(5000L);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .patch("/api/v1/users/pay")
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
            Member member = Member.builder()
                    .name("test")
                    .point(0L)
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            PayPointRequest request = new PayPointRequest(5000L);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .patch("/api/v1/users/pay")
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
}

package com.kau.capstone.domain.member.controller;

import com.kau.capstone.domain.member.dto.MemberInfoResponse;
import com.kau.capstone.domain.member.dto.ModifyMemberRequest;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.repository.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

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
                    .get("/api/v1/user")
                    .then()
                    .extract();
            MemberInfoResponse response = res.jsonPath().getObject("", MemberInfoResponse.class);

            // then
            SoftAssertions.assertSoftly(soft -> {
                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
                soft.assertThat(member.getSocialSite()).isEqualTo(response.socialSite());
                soft.assertThat(member.getName()).isEqualTo(response.name());
                soft.assertThat(member.getEmail()).isEqualTo(response.email());
                soft.assertThat(member.getPoint()).isEqualTo(response.point());
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
                    .patch("/api/v1/user")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        }
    }

    private String getCookie(String memberId) {
        return RestAssured.given()
                .queryParam("code", memberId)
                .when()
                .post("/api/v1/oauth2/test/code")
                .then()
                .log().all()
                .extract()
                .response()
                .cookie("JSESSIONID");
    }
}

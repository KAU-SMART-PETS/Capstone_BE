package com.kau.capstone.domain.vet.controller;

import com.kau.capstone.domain.vet.dto.MemberLocationRequest;
import com.kau.capstone.domain.vet.dto.VetDetailResponse;
import com.kau.capstone.domain.vet.dto.VetsResponse;
import com.kau.capstone.domain.vet.entity.Vet;
import com.kau.capstone.global.common.ControllerTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VetControllerTest extends ControllerTest {

    @Nested
    class getVetDetailInfo_성공_테스트 {

        @Test
        void 동물병원_세부_정보를_조회한다() {
            // given
            Vet vet = Vet.builder()
                    .name("동물병원1")
                    .address("경기도 고양시 어쩌고저쩌고")
                    .longitude(126.9033478)
                    .latitude(35.1615584)
                    .telephone("031-123-1234")
                    .build();
            vetRepository.save(vet);

            MemberLocationRequest request = new MemberLocationRequest(35.2031699, 126.8971756);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .post("/api/v1/vets/1")
                    .then()
                    .extract();
            VetDetailResponse response = res.jsonPath().getObject("", VetDetailResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(vet.getId()).isEqualTo(response.id());
                soft.assertThat(vet.getName()).isEqualTo(response.name());
                soft.assertThat(vet.getAddress()).isEqualTo(response.address());
                soft.assertThat(vet.getTelephone()).isEqualTo(response.telephone());
            });
        }
    }

    @Nested
    class getVetsInfo_성공_테스트 {

        @Test
        void 사용자에게_가장_가까운_동물병원_목록을_조회한다() {
            // given
            Vet vet1 = Vet.builder()
                    .name("동물병원1")
                    .address("경기도 고양시 어쩌고저쩌고")
                    .longitude(126.9033478)
                    .latitude(35.1615584)
                    .telephone("031-123-1234")
                    .build();
            Vet vet2 = Vet.builder()
                    .name("동물병원2")
                    .address("서울특별시 어쩌고저쩌고")
                    .longitude(123.4567)
                    .latitude(35.1234)
                    .telephone("02-1234-1234")
                    .build();
            vetRepository.saveAll(List.of(vet1, vet2));

            MemberLocationRequest request = new MemberLocationRequest(35.1234, 123.4567);

            // when

            ExtractableResponse<Response> res = RestAssured.given()
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .post("/api/v1/vets")
                    .then()
                    .extract();
            VetsResponse response = res.jsonPath().getObject("", VetsResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(response.vets().size()).isEqualTo(2);
                soft.assertThat(response.vets().get(0).name()).isEqualTo("동물병원2");
                soft.assertThat(response.vets().get(1).name()).isEqualTo("동물병원1");
            });
        }
    }
}

package com.kau.capstone.domain.vaccination.controller;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.domain.vaccination.entity.Vaccination;
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
class VaccinationControllerTest extends ControllerTest {

    @Nested
    class getVaccinationInfoForPet_성공_테스트 {

        @Test
        void 특정_반려동물의_예방접종_목록을_조회할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            Pet pet = Pet.builder()
                    .name("하늘이")
                    .age(1)
                    .build();
            petRepository.save(pet);

            Vaccination vaccination = Vaccination.builder()
                    .member(member)
                    .pet(pet)
                    .name("광견병")
                    .timeYear(2024)
                    .timeMonth(10)
                    .timeDay(16)
                    .build();
            vaccinationRepository.save(vaccination);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/pets/1/vaccination")
                    .then()
                    .extract();
            VaccinationsResponse response = res.jsonPath().getObject("", VaccinationsResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
                soft.assertThat(pet.getName()).isEqualTo(response.pet().name());
                soft.assertThat(response.vaccination().size()).isEqualTo(1);
                soft.assertThat(vaccination.getName()).isEqualTo(response.vaccination().get(0).name());
            });
        }
    }
}

package com.kau.capstone.domain.vaccination.controller;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.v1.vaccination.dto.CreateVaccinationRequest;
import com.kau.capstone.v1.vaccination.dto.PutVaccinationRequest;
import com.kau.capstone.v1.vaccination.dto.VaccinationsResponse;
import com.kau.capstone.entity.vaccination.Vaccination;
import com.kau.capstone.global.common.ControllerTest;
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
class VaccinationControllerTest extends ControllerTest {

    @Nested
    class createVaccinationInfoForPet_성공_테스트 {

        @Test
        void 특정_반려동물의_예방접종을_등록할_수_있다() {
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

            CreateVaccinationRequest request = new CreateVaccinationRequest("광견병", 2024, 10, 16);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .post("/api/v1/pets/1/vaccination")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }

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

            Vaccination vaccination = Vaccination.of(pet, new CreateVaccinationRequest("광견병", 2024, 10, 16));
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

    @Nested
    class putVaccinationInfoForPet_성공_테스트 {

        @Test
        void 보건정보를_수정할_수_있다() {
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

            Vaccination vaccination = Vaccination.of(pet, new CreateVaccinationRequest("광견병", 2024, 10, 16));
            vaccinationRepository.save(vaccination);

            PutVaccinationRequest request = new PutVaccinationRequest("켄넬코프", 2025, 11, 17);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .put("/api/v1/pets/1/vaccination/1")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        }
    }

    @Nested
    class deleteVaccinationInfoForPet_성공_테스트 {

        @Test
        void 보건정보를_삭제할_수_있다() {
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

            Vaccination vaccination = Vaccination.of(pet, new CreateVaccinationRequest("광견병", 2024, 10, 16));
            vaccinationRepository.save(vaccination);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .delete("/api/v1/pets/1/vaccination/1")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        }
    }
}

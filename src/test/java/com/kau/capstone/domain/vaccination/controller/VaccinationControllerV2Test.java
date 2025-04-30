package com.kau.capstone.domain.vaccination.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.pet.Gender;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.pet.PetType;
import com.kau.capstone.entity.vaccination.Vaccination;
import com.kau.capstone.global.common.ControllerTest;
import com.kau.capstone.v2.pet.dto.request.PetRegistReqV2;
import com.kau.capstone.v2.vaccination.dto.CreateVaccinationReqV2;
import com.kau.capstone.v2.vaccination.dto.PutVaccinationReqV2;
import com.kau.capstone.v2.vaccination.dto.VaccinationsResV2;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VaccinationControllerV2Test extends ControllerTest {

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

            PetRegistReqV2 petRegistReqV2 = new PetRegistReqV2("하늘이", 1, 1, 5.4, 1, null, null);
            Pet pet = Pet.of(petRegistReqV2, member);
            petRepository.save(pet);

            LocalDate vaccinatedDate = LocalDate.of(2024, 10, 16);
            CreateVaccinationReqV2 request = new CreateVaccinationReqV2("광견병", vaccinatedDate);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .post("/api/v2/pets/1/vaccination")
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

            PetRegistReqV2 petRegistReqV2 = new PetRegistReqV2("하늘이", 1, 1, 5.4, 1, null, null);
            Pet pet = Pet.of(petRegistReqV2, member);
            petRepository.save(pet);

            LocalDate vaccinatedDate = LocalDate.of(2024, 10, 16);
            Vaccination vaccination = Vaccination.of(pet, new CreateVaccinationReqV2("광견병", vaccinatedDate));
            vaccinationRepository.save(vaccination);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v2/pets/1/vaccination")
                    .then()
                    .extract();
            VaccinationsResV2 response = res.jsonPath().getObject("data", VaccinationsResV2.class);

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

            PetRegistReqV2 petRegistReqV2 = new PetRegistReqV2("하늘이", 1, 1, 5.4, 1, null, null);
            Pet pet = Pet.of(petRegistReqV2, member);
            petRepository.save(pet);

            LocalDate vaccinatedDate = LocalDate.of(2024, 10, 16);
            Vaccination vaccination = Vaccination.of(pet, new CreateVaccinationReqV2("광견병", vaccinatedDate));
            vaccinationRepository.save(vaccination);

            LocalDate newVaccinatedDate = LocalDate.of(2025, 11, 17);
            PutVaccinationReqV2 request = new PutVaccinationReqV2("켄넬코프", newVaccinatedDate);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .put("/api/v2/pets/1/vaccination/1")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
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

            PetRegistReqV2 petRegistReqV2 = new PetRegistReqV2("하늘이", 1, 1, 5.4, 1, null, null);
            Pet pet = Pet.of(petRegistReqV2, member);
            petRepository.save(pet);

            LocalDate vaccinatedDate = LocalDate.of(2024, 10, 16);
            Vaccination vaccination = Vaccination.of(pet, new CreateVaccinationReqV2("광견병", vaccinatedDate));
            vaccinationRepository.save(vaccination);

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .delete("/api/v2/pets/1/vaccination/1")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }
}

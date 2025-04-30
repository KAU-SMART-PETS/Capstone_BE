//package com.kau.capstone.domain.member.controller;
//
//import com.kau.capstone.v1.auth.util.SocialSite;
//import com.kau.capstone.v1.member.dto.MemberInfoResponse;
//import com.kau.capstone.v1.member.dto.ModifyMemberRequest;
//import com.kau.capstone.v1.member.dto.OwnedPetsResponse;
//import com.kau.capstone.entity.member.Member;
//import com.kau.capstone.entity.member.OwnedPet;
//import com.kau.capstone.entity.pet.Pet;
//import com.kau.capstone.entity.point.Point;
//import com.kau.capstone.global.common.ControllerTest;
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
//import static org.assertj.core.api.SoftAssertions.assertSoftly;
//
//@SuppressWarnings("NonAsciiCharacters")
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//class MemberControllerTest extends ControllerTest {
//
//    @Nested
//    class getMemberInfo_성공_테스트 {
//
//        @Test
//        void 유저_정보를_조회한다() {
//            // given
//
//            Member saveMember = Member.builder()
//                    .socialSite("test")
//                    .platformId("1")
//                    .name("test")
//                    .email("test@test.com")
//                    .socialSite(SocialSite.NAVER.name())
//                    .smsOptIn(false)
//                    .emailOptIn(true)
//                    .build();
//            Member member = memberRepository.save(saveMember);
//
//            Point point = Point.of(member);
//            pointRepository.save(point);
//
//            member.connectPoint(point);
//
//
//            // when
//            String cookie = getCookie("1");
//
//            ExtractableResponse<Response> res = RestAssured.given()
//                    .cookie("JSESSIONID", cookie)
//                    .contentType("application/json")
//                    .when()
//                    .get("/api/v1/users")
//                    .then()
//                    .extract();
//            MemberInfoResponse response = res.jsonPath().getObject("", MemberInfoResponse.class);
//
//            // then
//            assertSoftly(soft -> {
//                soft.assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
//                soft.assertThat(member.getName()).isEqualTo(response.name());
//                soft.assertThat(member.getEmail()).isEqualTo(response.email());
//                soft.assertThat(member.getPhoneNumber()).isEqualTo(response.phoneNumber());
//                soft.assertThat(member.getPoint().getAmount()).isEqualTo(response.point());
//                soft.assertThat(member.getSocialSite()).isEqualTo(response.socialSite());
//                soft.assertThat(member.getSmsOptIn()).isEqualTo(response.smsOptIn());
//                soft.assertThat(member.getEmailOptIn()).isEqualTo(response.emailOptIn());
//
//            });
//        }
//    }
//
//    @Nested
//    class putMemberInfo_성공_테스트 {
//
//        @Test
//        void 유저_정보를_수정할_수_있다() {
//            // given
//            ModifyMemberRequest request = new ModifyMemberRequest("update@update.com", "010-1234-1234", false, false);
//
//            // when
//            String cookie = getCookie("1");
//
//            ExtractableResponse<Response> res = RestAssured.given()
//                    .cookie("JSESSIONID", cookie)
//                    .contentType("application/json")
//                    .body(request)
//                    .when()
//                    .patch("/api/v1/users")
//                    .then()
//                    .extract();
//
//            // then
//            assertThat(res.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
//        }
//    }
//
//    @Nested
//    class getOwnerPets_성공_테스트 {
//
//        @Test
//        void 사용자가_등록한_반려동물_목록을_볼_수_있다() {
//            // given
//            Member member = Member.builder()
//                    .name("test")
//                    .platformId("1")
//                    .build();
//            memberRepository.save(member);
//
//            Pet pet1 = Pet.builder()
//                    .name("test1")
//                    .age(1)
//                    .build();
//            Pet pet2 = Pet.builder()
//                    .name("test2")
//                    .age(2)
//                    .build();
//            Pet pet3 = Pet.builder()
//                    .name("test3")
//                    .age(3)
//                    .build();
//            petRepository.saveAll(List.of(pet1, pet2, pet3));
//
//            OwnedPet ownedPet1 = OwnedPet.builder()
//                    .member(member)
//                    .pet(pet1)
//                    .build();
//            OwnedPet ownedPet2 = OwnedPet.builder()
//                    .member(member)
//                    .pet(pet3)
//                    .build();
//            ownedPetRepository.saveAll(List.of(ownedPet1, ownedPet2));
//
//            // when
//            String cookie = getCookie("1");
//
//            ExtractableResponse<Response> res = RestAssured.given()
//                    .cookie("JSESSIONID", cookie)
//                    .contentType("application/json")
//                    .when()
//                    .get("/api/v1/users/pets")
//                    .then()
//                    .extract();
//            OwnedPetsResponse response = res.jsonPath().getObject("", OwnedPetsResponse.class);
//
//            // then
//            assertSoftly(soft -> {
//                soft.assertThat(response.pets().size()).isEqualTo(2);
//                soft.assertThat(response.pets().get(0).id()).isEqualTo(1L);
//                soft.assertThat(response.pets().get(1).id()).isEqualTo(3L);
//            });
//        }
//    }
//}

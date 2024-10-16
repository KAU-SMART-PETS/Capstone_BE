package com.kau.capstone.domain.bluetooth.controller;

import com.kau.capstone.domain.bluetooth.dto.BluetoothRegistryRequest;
import com.kau.capstone.domain.bluetooth.dto.OwnedBluetoothResponse;
import com.kau.capstone.domain.bluetooth.entity.Bluetooth;
import com.kau.capstone.domain.bluetooth.entity.member.OwnedBluetooth;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.global.common.ControllerTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BluetoothControllerTest extends ControllerTest {

    @Nested
    class createOwnedBluetooth_성공_테스트 {

        @Test
        void 사용자가_블루투스_기기를_등록할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .platformId("1")
                    .build();
            memberRepository.save(member);

            BluetoothRegistryRequest request = new BluetoothRegistryRequest("test", "macAddressTest");

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .post("/api/v1/bluetooth")
                    .then()
                    .extract();

            // then
            assertThat(res.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }

    @Nested
    class getOwnedBluetoothInfo_성공_테스트 {

        @Test
        void 사용자가_등록한_블루투스_기기_목록을_볼_수_있다() {
            // given
            Member member1 = Member.builder()
                    .name("test1")
                    .platformId("1")
                    .build();
            Member member2 = Member.builder()
                    .name("test2")
                    .platformId("2")
                    .build();
            memberRepository.saveAll(List.of(member1, member2));

            Bluetooth bluetooth1 = Bluetooth.builder()
                    .name("블루투스 1")
                    .macAddress("123")
                    .build();
            Bluetooth bluetooth2 = Bluetooth.builder()
                    .name("블루투스 1")
                    .macAddress("123")
                    .build();
            bluetoothRepository.saveAll(List.of(bluetooth1, bluetooth2));

            OwnedBluetooth ownedBluetooth1 = OwnedBluetooth.builder()
                    .member(member1)
                    .bluetooth(bluetooth1)
                    .build();
            OwnedBluetooth ownedBluetooth2 = OwnedBluetooth.builder()
                    .member(member2)
                    .bluetooth(bluetooth2)
                    .build();
            ownedBluetoothRepository.saveAll(List.of(ownedBluetooth1, ownedBluetooth2));

            // when
            String cookie = getCookie("1");

            ExtractableResponse<Response> res = RestAssured.given()
                    .cookie("JSESSIONID", cookie)
                    .contentType("application/json")
                    .when()
                    .get("/api/v1/bluetooth")
                    .then()
                    .extract();

            OwnedBluetoothResponse response = res.jsonPath().getObject("", OwnedBluetoothResponse.class);

            // then
            assertSoftly(soft -> {
                soft.assertThat(response.bluetooth().size()).isEqualTo(1);
                soft.assertThat(bluetooth1.getId()).isEqualTo(response.bluetooth().get(0).id());
            });
        }
    }
}

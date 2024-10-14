package com.kau.capstone.global.common;

import com.kau.capstone.domain.bluetooth.repository.BluetoothRepository;
import com.kau.capstone.domain.bluetooth.repository.OwnedBluetoothRepository;
import com.kau.capstone.domain.food.repository.FoodRepository;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.vet.repository.VetRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected PetRepository petRepository;

    @Autowired
    protected OwnedPetRepository ownedPetRepository;

    @Autowired
    protected VetRepository vetRepository;

    @Autowired
    protected FoodRepository foodRepository;

    @Autowired
    protected BluetoothRepository bluetoothRepository;

    @Autowired
    protected OwnedBluetoothRepository ownedBluetoothRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    protected String getCookie(String memberId) {
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

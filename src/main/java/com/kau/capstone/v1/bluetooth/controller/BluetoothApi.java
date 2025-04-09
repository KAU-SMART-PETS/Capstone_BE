package com.kau.capstone.v1.bluetooth.controller;

import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v1.auth.util.LoginUser;
import com.kau.capstone.v1.bluetooth.dto.BluetoothRegistryRequest;
import com.kau.capstone.v1.bluetooth.dto.OwnedBluetoothResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "블루투스 API")
public interface BluetoothApi {

    @Operation(summary = "블루투스 기기 등록", description = "유저가 등록한 블루투스 기기를 등록할 때 사용합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "블루투스 기기 등록 성공",
            content = @Content(schema = @Schema(implementation = BluetoothRegistryRequest.class))
    )
    @PostMapping("/api/v1/bluetooth")
    ResponseEntity<Void> createOwnedBluetooth(@LoginUser LoginInfo loginInfo,
                                              @RequestBody BluetoothRegistryRequest request);

    @Operation(summary = "블루투스 기기 목록 조회", description = "유저가 등록한 블루투스 기기 목록을 조회할 때 사용합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "블루투스 기기 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = OwnedBluetoothResponse.class))
    )
    @GetMapping("/api/v1/bluetooth")
    ResponseEntity<OwnedBluetoothResponse> getOwnedBluetoothInfo(@LoginUser LoginInfo loginInfo);
}

package com.kau.capstone.domain.bluetooth.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.bluetooth.dto.BluetoothRegistryRequest;
import com.kau.capstone.domain.bluetooth.dto.OwnedBluetoothResponse;
import com.kau.capstone.domain.bluetooth.service.BluetoothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BluetoothController {

    private final BluetoothService bluetoothService;

    @PostMapping("/api/v1/bluetooth")
    public ResponseEntity<Void> createOwnedBluetooth(@LoginUser LoginInfo loginInfo,
                                                      @RequestBody BluetoothRegistryRequest request) {
        bluetoothService.registryOwnedBluetooth(loginInfo.memberId(), request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/v1/bluetooth")
    public ResponseEntity<OwnedBluetoothResponse> getOwnedBluetoothInfo(@LoginUser LoginInfo loginInfo) {
        OwnedBluetoothResponse response = bluetoothService.getOwnedBluetoothInfo(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }
}

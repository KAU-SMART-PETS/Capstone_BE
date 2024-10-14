package com.kau.capstone.domain.bluetooth.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.bluetooth.dto.OwnedBluetoothResponse;
import com.kau.capstone.domain.bluetooth.service.BluetoothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BluetoothController {

    private final BluetoothService bluetoothService;

    @GetMapping("/api/v1/bluetooth")
    public ResponseEntity<OwnedBluetoothResponse> getOwnedBluetoothInfo(@LoginUser LoginInfo loginInfo) {
        OwnedBluetoothResponse response = bluetoothService.getOwnedBluetoothInfo(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }
}

package com.kau.capstone.v1.bluetooth.dto;

import com.kau.capstone.entity.bluetooth.Bluetooth;
import lombok.Builder;

@Builder
public record OneOwnedBluetoothResponse(
        Long id,
        String name,
        String macAddress
) {

    public static OneOwnedBluetoothResponse toResponse(Bluetooth bluetooth) {
        return OneOwnedBluetoothResponse.builder()
                .id(bluetooth.getId())
                .name(bluetooth.getName())
                .macAddress(bluetooth.getMacAddress())
                .build();
    }
}

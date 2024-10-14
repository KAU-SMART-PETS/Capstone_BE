package com.kau.capstone.domain.bluetooth.dto;

import com.kau.capstone.domain.bluetooth.entity.Bluetooth;
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

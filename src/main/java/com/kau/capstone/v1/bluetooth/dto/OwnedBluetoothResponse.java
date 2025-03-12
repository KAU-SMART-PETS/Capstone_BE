package com.kau.capstone.v1.bluetooth.dto;

import com.kau.capstone.entity.bluetooth.Bluetooth;
import lombok.Builder;

import java.util.List;

@Builder
public record OwnedBluetoothResponse(
        List<OneOwnedBluetoothResponse> bluetooth
) {

    public static OwnedBluetoothResponse toResponse(List<Bluetooth> bluetooth) {
        List<OneOwnedBluetoothResponse> responses = bluetooth.stream()
                .map(OneOwnedBluetoothResponse::toResponse)
                .toList();

        return OwnedBluetoothResponse.builder()
                .bluetooth(responses)
                .build();
    }
}

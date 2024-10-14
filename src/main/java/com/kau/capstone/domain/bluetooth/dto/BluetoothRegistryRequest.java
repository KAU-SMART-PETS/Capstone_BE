package com.kau.capstone.domain.bluetooth.dto;

public record BluetoothRegistryRequest(
        String name,
        String macAddress
) {
}

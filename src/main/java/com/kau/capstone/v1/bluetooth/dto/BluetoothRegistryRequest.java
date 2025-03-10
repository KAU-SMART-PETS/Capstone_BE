package com.kau.capstone.v1.bluetooth.dto;

public record BluetoothRegistryRequest(
        String name,
        String macAddress
) {
}

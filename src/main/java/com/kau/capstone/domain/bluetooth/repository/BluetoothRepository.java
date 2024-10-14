package com.kau.capstone.domain.bluetooth.repository;

import com.kau.capstone.domain.bluetooth.entity.Bluetooth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BluetoothRepository extends JpaRepository<Bluetooth, Long> {
}

package com.kau.capstone.entity.bluetooth.repository;

import com.kau.capstone.entity.bluetooth.Bluetooth;
import com.kau.capstone.entity.bluetooth.OwnedBluetooth;
import com.kau.capstone.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnedBluetoothRepository extends JpaRepository<OwnedBluetooth, Long> {

    @Query("SELECT o.bluetooth FROM OwnedBluetooth o WHERE o.member = :member")
    List<Bluetooth> findBluetoothByMember(@Param("member") Member member);
}

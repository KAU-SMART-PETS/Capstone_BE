package com.kau.capstone.domain.bluetooth.repository;

import com.kau.capstone.domain.bluetooth.entity.Bluetooth;
import com.kau.capstone.domain.bluetooth.entity.member.OwnedBluetooth;
import com.kau.capstone.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnedBluetoothRepository extends JpaRepository<OwnedBluetooth, Long> {

    @Query("SELECT o.bluetooth FROM OwnedBluetooth o WHERE o.member = :member")
    List<Bluetooth> findBluetoothByMember(@Param("member") Member member);
}

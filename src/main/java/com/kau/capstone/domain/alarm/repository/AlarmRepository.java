package com.kau.capstone.domain.alarm.repository;

import com.kau.capstone.domain.alarm.entity.Alarm;
import com.kau.capstone.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    @Query("SELECT a FROM Alarm a WHERE a.member = :member AND a.isVisible = true")
    List<Alarm> findAllAlarmByMember(@Param("member") Member member);

    @Query("SELECT a FROM Alarm a WHERE a.member = :member AND a.type = :type")
    Alarm findAlarmByMemberAndType(@Param("member") Member member,
                                   @Param("type") Long type);
}

package com.kau.capstone.domain.member.repository;

import com.kau.capstone.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByPlatformId(String platformId);

    @Modifying
    @Query("UPDATE Member m SET m.refreshToken = :refreshToken WHERE m.id = :memberId")
    void updateRefreshTokenByMemberId(@Param("memberId") Long memberId, @Param("refreshToken") String refreshToken);
}

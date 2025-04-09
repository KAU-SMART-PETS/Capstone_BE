package com.kau.capstone.entity.point.repository;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.point.Point;
import com.kau.capstone.v2.point.exception.PointNotFoundExceptionV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("SELECT p FROM Point p WHERE p.member = :member")
    Optional<Point> findByMember(@Param("member") Member member);

    default Point getByMember(Member member){
        return findByMember(member).orElseThrow(PointNotFoundExceptionV2::new);
    };
}

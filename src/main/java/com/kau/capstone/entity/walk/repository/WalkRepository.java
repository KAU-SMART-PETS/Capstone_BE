package com.kau.capstone.entity.walk.repository;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.walk.Walk;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalkRepository extends JpaRepository<Walk,Long> {

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet AND YEAR(w.startTime) = :year AND MONTH(w.startTime) = :month")
    List<Walk> findByPetAndYearAndMonth(@Param("pet") Pet pet, @Param("year") int year, @Param("month") int month);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet ORDER BY w.startTime DESC")
    List<Walk> findTop5ByPetOrderByDataIntDtDesc(@Param("pet") Pet pet, Pageable pageable);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet AND FUNCTION('DATE', w.startTime) = :walkDate")
    List<Walk> findDailyWalksByPetAndWalkDate(@Param("pet") Pet pet, @Param("walkDate") LocalDate walkDate);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet AND w.startTime BETWEEN :startDateTime AND :endDateTime")
    List<Walk> findWeeklyWalks(@Param("pet") Pet pet, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime
    );

    List<Walk> findTop20ByMemberOrderByStartTimeDesc(Member member);



    default List<Walk> getRecentWalks(Member member) {
        return findTop20ByMemberOrderByStartTimeDesc(member);
    }

    default List<Walk> getDailyWalks(Pet pet, LocalDate walkDate) {
        return findDailyWalksByPetAndWalkDate(pet, walkDate);
    }

    default List<Walk> getWalkCalendar(Pet pet, int year, int month) {
        return findByPetAndYearAndMonth(pet, year, month);
    }

    default List<Walk> getWeeklyWalks(Pet pet, LocalDateTime monday, LocalDateTime sunday){
        return findWeeklyWalks(pet, monday, sunday);
    }
}


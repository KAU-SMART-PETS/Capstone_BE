package com.kau.capstone.entity.walk.repository;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.walk.Walk;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalkRepository extends JpaRepository<Walk,Long> {

    List<Walk> findByPetAndDataIntDt(Pet pet, LocalDate dataIntDt);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet AND YEAR(w.startTime) = :year AND MONTH(w.startTime) = :month")
    List<Walk> findByPetAndYearAndMonth(@Param("pet") Pet pet, @Param("year") int year, @Param("month") int month);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet ORDER BY w.startTime DESC")
    List<Walk> findTop5ByPetOrderByDataIntDtDesc(@Param("pet") Pet pet, Pageable pageable);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet AND FUNCTION('DATE', w.startTime) = :walkDate")
    List<Walk> findDailyWalksByPetAndWalkDate(@Param("pet") Pet pet, @Param("walkDate") LocalDate walkDate);

    List<Walk> findTop20ByMemberOrderByStartTimeDesc(Member member);


    default List<Walk> getRecentWalksByMember(Member member) {
        return findTop20ByMemberOrderByStartTimeDesc(member);
    }

    default List<Walk> getDailyWalksByPetAndWalkDate(Pet pet, LocalDate walkDate) {
        return findDailyWalksByPetAndWalkDate(pet, walkDate);
    }


}


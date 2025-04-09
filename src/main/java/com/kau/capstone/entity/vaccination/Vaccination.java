package com.kau.capstone.entity.vaccination;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.pet.Pet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vaccination {

    @Id
    @Comment("알람 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Comment("사용자 연결")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @Comment("반려동물 연결")
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Comment("예바접종 이름")
    private String name;

    @Comment("연도")
    private Integer timeYear;

    @Comment("월")
    private Integer timeMonth;

    @Comment("일")
    private Integer timeDay;

    public void modify(String name, Integer year, Integer month, Integer day) {
        this.name = name;
        this.timeYear = year;
        this.timeMonth = month;
        this.timeDay = day;
    }
}

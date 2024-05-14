package com.kau.capstone.domain.pet.entity;

import com.kau.capstone.global.common.BaseEntity;
import com.kau.capstone.global.common.Gender;
import com.kau.capstone.global.common.PetType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet extends BaseEntity {

    @Id
    @Comment("반려동물 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("반려동물 이름")
    @Column(length = 10, nullable = false)
    private String name;

    @Comment("종류")
    @Enumerated(EnumType.STRING)
    private PetType petType;

    @Comment("성별")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Comment("체중")
    private float weight;

    @Comment("나이")
    @Column(nullable = false)
    private Integer age;

    @Comment("반려동물 이미지")
    private String imageUrl;

    public Pet(Long id, String name, PetType petType, Gender gender, float weight, Integer age,
        String imageUrl) {
        this.id = id;
        this.name = name;
        this.petType = petType;
        this.gender = gender;
        this.weight = weight;
        this.age = age;
        this.imageUrl = imageUrl;
    }
}


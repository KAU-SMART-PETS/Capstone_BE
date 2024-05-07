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
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("반려동물 식별자")
    private Long id;

    @Column(length = 10)
    @Comment("반려동물 이름")
    private String name;

    @Enumerated(EnumType.STRING)
    @Comment("종류")
    private PetType petType;

    @Enumerated(EnumType.STRING)
    @Comment("성별")
    private Gender gender;

    @Comment("체중")
    private float weight;

    @Comment("나이")
    private Integer age;

    @Comment("반려동물 이미지")
    private String imageUrl;

    @Builder
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


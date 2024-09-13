package com.kau.capstone.domain.pet.entity;

import com.kau.capstone.domain.pet.dto.request.UpdatePetInfoRequest;
import com.kau.capstone.global.common.BaseEntity;
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
    private double weight;

    @Comment("나이")
    @Column(nullable = false)
    private Integer age;

    @Comment("반려동물 이미지")
    private String imageUrl;

    public Pet(Long id, String name, PetType petType, Gender gender, double weight, Integer age,
        String imageUrl) {
        this.id = id;
        this.name = name;
        this.petType = petType;
        this.gender = gender;
        this.weight = weight;
        this.age = age;
        this.imageUrl = imageUrl;
    }

    public void updatePet(UpdatePetInfoRequest updatePetInfoRequest) {
        this.age = updatePetInfoRequest.age();
        this.name = updatePetInfoRequest.name();
        this.petType = PetType.fromInt(updatePetInfoRequest.petType());
        this.gender = Gender.fromInt(updatePetInfoRequest.gender());
        this.weight = updatePetInfoRequest.weight();
    }

    public void deletePet() {
        this.delete(LocalDateTime.now());
    }
}


package com.kau.capstone.domain.pet.dto.request;

import com.kau.capstone.domain.pet.entity.Gender;
import com.kau.capstone.domain.pet.entity.PetType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
public class PetRegistReqV2 {

    @NotEmpty(message = "이름을 입력해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{1,10}$", message = "이름은 특수문자를 제외한 1~10자리여야 합니다.")
    private String name;
    private Integer petType;
    private Integer gender;
    private Double weight;
    private Integer age;
    private String breed;
    private MultipartFile image;


    public String getName() {
        return this.name;
    }

    public PetType getPetType() {
        return PetType.fromInt(this.petType);
    }

    public Gender getGender() {
        return Gender.fromInt(this.gender);
    }

    public Double getWeight() {
        return this.weight;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getBreed() {
        return this.breed;
    }

    public MultipartFile getImage() {
        return this.image;
    }
}

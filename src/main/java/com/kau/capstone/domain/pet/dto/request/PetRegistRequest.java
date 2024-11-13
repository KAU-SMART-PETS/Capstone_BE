package com.kau.capstone.domain.pet.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PetRegistRequest {

    @NotEmpty(message = "이름을 입력해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{1,10}$", message = "이름은 특수문자를 제외한 1~10자리여야 합니다.")
    private String name;

    private Integer petType;

    private Integer gender;

    private Double weight;

    private Integer age;

    private MultipartFile image;
}

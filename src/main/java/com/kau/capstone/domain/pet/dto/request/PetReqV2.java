package com.kau.capstone.domain.pet.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record PetReqV2(
    @NotEmpty(message = "이름을 입력해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{1,10}$", message = "이름은 특수문자를 제외한 1~10자리여야 합니다.")
    String name,
    Integer petType,
    Integer gender,
    Double weight,
    Integer age,
    String breed
) {

}

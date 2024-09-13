package com.kau.capstone.domain.pet.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdatePetInfoRequest(
    @NotNull(message = "이름을 입력해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{1,10}$", message = "이름은 특수문자를 제외한 1~10자리여야 합니다.")
    String name,
    @NotNull
    Integer petType,
    @NotNull
    Integer gender,
    double weight,
    @NotNull
    Integer age
) {

}

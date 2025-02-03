package com.kau.capstone.domain.pet.exception;

import com.kau.capstone.global.exception.ApplicationException;

public class PetAndMemberNotMatchedException extends ApplicationException {

    public PetAndMemberNotMatchedException(String message) {
        super(message);
    }
}

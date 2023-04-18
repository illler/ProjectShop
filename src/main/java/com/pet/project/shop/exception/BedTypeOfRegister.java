package com.pet.project.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BedTypeOfRegister extends RuntimeException {
    public BedTypeOfRegister(String message) {
        super(message);
    }

}


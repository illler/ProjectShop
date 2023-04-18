package com.pet.project.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GoodNotFoundException extends RuntimeException {
    public GoodNotFoundException(String message) {
        super(message);
    }

}


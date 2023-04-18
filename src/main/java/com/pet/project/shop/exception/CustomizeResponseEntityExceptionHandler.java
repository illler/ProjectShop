package com.pet.project.shop.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Objects;

@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GoodNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleGoodNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BedTypeOfRegister.class)
    public final ResponseEntity<ErrorDetails> handleBadRequest(Exception ex, WebRequest request) {
        String message = ex.getMessage();
        if (message.contains("Validation failed for classes")) {
            int startIndex = message.indexOf("[");
            int endIndex = message.lastIndexOf("]");
            String violations = message.substring(startIndex, endIndex);
            return new ResponseEntity<>(new ErrorDetails(LocalDate.now(),
                    "Validation failed: " + violations, request.getDescription(false)), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ErrorDetails(LocalDate.now(),
                    ex.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);    }

}

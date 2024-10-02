package ru.kata.spring.boot_security.demo.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {
    //404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundUser(UsernameNotFoundException e) {
        return new ErrorResponse(e.getMessage(), e.getMessage());
    }
    //500
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse serverError(Throwable e) {
        return new ErrorResponse("Произошла непредвиденная ошибка.",e.getMessage());
    }

    @Getter
    public static class ErrorResponse {
        String error;
        String message;
        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

    }

}

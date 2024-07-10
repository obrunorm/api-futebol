package com.meli.apifutebol.infra;

import com.meli.apifutebol.exceptions.DuplicateNameException;
import com.meli.apifutebol.exceptions.InvalidClubDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateNameException.class)
    private ResponseEntity<String> nomeDuplicado(DuplicateNameException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(InvalidClubDataException.class)
    public ResponseEntity<Object> handleInvalidClubDataException(InvalidClubDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

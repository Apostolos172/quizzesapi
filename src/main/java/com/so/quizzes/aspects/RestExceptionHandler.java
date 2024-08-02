package com.so.quizzes.aspects;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleEntityNotFoundException(EntityNotFoundException exc) {
        var errorResponse = new EntityErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleException(OperationNotAllowed exc) {
        var errorResponse = new EntityErrorResponse();
        errorResponse.setStatus(HttpStatus.PRECONDITION_FAILED.value());
        errorResponse.setMessage(exc.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.PRECONDITION_FAILED);
    }
}

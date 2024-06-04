package com.accolite.ecommercebackend.Exception;

import com.accolite.ecommercebackend.dto.Response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class UserExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userAlreadyExistException(UserAlreadyExistException exception, WebRequest request){
        ExceptionResponse message = new ExceptionResponse(exception.getMessage(), HttpStatus.FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(message);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserAlreadyExistException exception, WebRequest request){
        ExceptionResponse message = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ExceptionResponse> IncorrectPasswordException(IncorrectPasswordException exception, WebRequest request){
        ExceptionResponse message = new ExceptionResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @ExceptionHandler(ProductQuantityExceededException.class)
    public ResponseEntity<ExceptionResponse> productQuantityExceededException(ProductQuantityExceededException exception, WebRequest request) {
        ExceptionResponse message = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}

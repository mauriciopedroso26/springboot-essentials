package br.com.devdojo.demo.handler;

import br.com.devdojo.demo.error.ResourceNotFoundDetails;
import br.com.devdojo.demo.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ResourceNotFoundDetails datails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resouce not found")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();

        return new ResponseEntity<>(datails, HttpStatus.NOT_FOUND);
    }
}

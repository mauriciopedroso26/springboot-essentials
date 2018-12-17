package br.com.devdojo.demo.handler;

import br.com.devdojo.demo.error.ResourceNotFoundDetails;
import br.com.devdojo.demo.error.ResourceNotFoundException;
import br.com.devdojo.demo.error.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ResourceNotFoundDetails datails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resouce not found")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();

        return new ResponseEntity<>(datails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationErrorDetails datails = ValidationErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Field Validation Error")
                .detail("Field Validation Error")
                .developerMessage(exception.getClass().getName())
                .field(fields)
                .fieldMessage(fieldMessages)
                .build();

        return new ResponseEntity<>(datails, HttpStatus.NOT_FOUND);
    }
}

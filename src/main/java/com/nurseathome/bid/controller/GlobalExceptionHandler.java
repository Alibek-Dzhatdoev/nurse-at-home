package com.nurseathome.bid.controller;

import com.nurseathome.bid.model.exceptions.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Optional.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        log.warn(ex.getMessage(), ex);

        return switch (ex) {
            case MethodArgumentNotValidException methodArgumentNotValidException ->
                    onMethodArgumentNotValidException(methodArgumentNotValidException);
            case DataIntegrityViolationException dataIntegrityViolationException ->
                    onDataIntegrityViolationException(dataIntegrityViolationException);
            case HandlerMethodValidationException handlerMethodValidationException ->
                    onHandlerMethodValidationException(handlerMethodValidationException);
            case ResponseStatusException responseStatusException ->
                    onHandleResponseStatusException(responseStatusException);
            default -> onHandleResponseStatusException(new ResponseStatusException(INTERNAL_SERVER_ERROR, ex.getMessage()));
        };
    }

    private ResponseEntity<ApiError> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = of(e.getBindingResult())
                .map(Errors::getFieldError)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Invalid argument");
        return onHandleResponseStatusException(new ResponseStatusException(BAD_REQUEST, message));
    }

    private ResponseEntity<ApiError> onHandlerMethodValidationException(HandlerMethodValidationException e) {
        String message = e.getAllErrors()
                .getFirst()
                .getDefaultMessage();
        return onHandleResponseStatusException(new ResponseStatusException(BAD_REQUEST, message));
    }

    private ResponseEntity<ApiError> onHandleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode statusCode = ex.getStatusCode();
        return status(statusCode).body(
                ApiError.builder()
                        .externalServiceStatus(statusCode.value())
                        .message(ex.getReason())
                        .build());
    }

    private ResponseEntity<ApiError> onDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause().getMessage();
        if(message.contains("ограничение уникальности \"patients_email_key\"")){
            message = "Этот еmail уже занят";
        } else if(message.contains("ограничение уникальности \"patients_mobile_phone_key\"")) {
            message = "Этот номер телефона уже занят";
        }
        return onHandleResponseStatusException(new ResponseStatusException(BAD_REQUEST, message, ex));
    }
}
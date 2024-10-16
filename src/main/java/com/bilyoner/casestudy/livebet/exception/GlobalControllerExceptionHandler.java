/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.exception;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * BadRequestException
 *
 * @author f0012325
 * @date 5.10.2024
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    @Value("${coupon.timeout}")
    private String timeOut;
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleNotFoundExceptions(ObjectNotFoundException ex) {
        logger.error("Object not found: ", ex);
        return new ResponseEntity<>("Object not found. " +
                "Please verify your parameters. Check logs for more details.", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalExceptions(Exception ex) {
        logger.error("Something went wrong: ", ex);
        return new ResponseEntity<>("Something went wrong. Please try again later.", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<String> handleJpaSystemExceptions(JpaSystemException ex) {
        logger.error("Something went wrong: ", ex);
        return new ResponseEntity<>("Timeout error. Please try again later. Exceed to timeout limit. Limit is:" + timeOut, HttpStatus.NOT_FOUND);
    }
}

package com.bilyoner.casestudy.livebet.exception;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalControllerExceptionHandlerTest {

    @InjectMocks
    private GlobalControllerExceptionHandler exceptionHandler;

    @Mock
    private MethodArgumentNotValidException exception;
    @Mock
    private ObjectNotFoundException notfoundException;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleValidationExceptions() {
        // Arrange
        FieldError fieldError = new FieldError("objectName", "fieldName", "errorMessage");

        BindingResult result = mock(BindingResult.class);
        when(result.getAllErrors()).thenReturn(createBindingResultWithErrors(fieldError));
        when(exception.getBindingResult()).thenReturn(result);

        // Act
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleValidationExceptions(exception);

        // Assert
        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("fieldName", "errorMessage");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrors, response.getBody());
    }

    @Test
    public void testHandleNotFoundExceptions() {
        // Act
        ResponseEntity<String> response = exceptionHandler.handleNotFoundExceptions(notfoundException);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Object not found. Please verify your parameters. Check logs for more details.", response.getBody());
    }

    // Helper method to create a BindingResult with errors
    private List<ObjectError> createBindingResultWithErrors(FieldError... errors) {
        List<ObjectError> objectErrors = new ArrayList<>();
        for (FieldError error : errors) {
            objectErrors.add(error);
        }
        return objectErrors;
    }
}

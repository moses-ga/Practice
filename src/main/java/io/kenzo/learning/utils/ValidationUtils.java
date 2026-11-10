package io.kenzo.learning.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** Bean-validates an object and builds a 400 response with field errors, for API controllers. */
@Component
public class ValidationUtils {

    private final Validator validator;

    public ValidationUtils(Validator validator) {
        this.validator = validator;
    }

    /** Returns a 400 response when invalid, or {@code null} when the object passes validation. */
    public <T> ResponseEntity<Map<String, Object>> validateAndBuildResponse(T objectToValidate) {
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if (violations.isEmpty()) {
            return null;
        }
        Map<String, Object> errorDetails = new HashMap<>();
        for (ConstraintViolation<T> violation : violations) {
            errorDetails.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Validation failed please fill all required fields");
        errorResponse.put("details", errorDetails);
        errorResponse.put("action", 0);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

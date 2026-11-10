package io.kenzo.learning.exception;

import io.kenzo.learning.utils.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Turns service exceptions into JSON responses for the REST controllers under controllers.apis. */
@RestControllerAdvice(basePackages = "io.kenzo.learning.controllers.apis")
public class ApiExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<GeneralResponse> notFound(ModelNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GeneralResponse(0, e.getMessage()));
    }
}

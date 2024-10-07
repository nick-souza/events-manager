package com.nicolas.EventManager.exception;

import com.nicolas.EventManager.dtos.ExceptionDto;
import io.micrometer.common.lang.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionDto> handleGenericException(Exception ex) {
        ExceptionDto response = new ExceptionDto(ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionDto> handleRecordNotFound(Exception ex) {
        ExceptionDto response = new ExceptionDto(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionDto> handleBadRequest(Exception ex) {
        ExceptionDto response = new ExceptionDto(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    // DTO Property Validation:
    @Override @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        var validationErrors = ex.getBindingResult().getFieldErrors().stream().map(err -> err.getDefaultMessage() != null ? err.getDefaultMessage() : err.getField() + "can't be null").toList();

        ExceptionDto response = new ExceptionDto(validationErrors.get(0));

        return ResponseEntity.badRequest().body(response);
    }
}

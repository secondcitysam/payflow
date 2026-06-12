package com.payflow.switchservice.handler;

import com.payflow.common.dto.ApiResponse;
import com.payflow.switchservice.exception.BusinessException;
import com.payflow.switchservice.exception.RateLimitExceededException;
import com.payflow.switchservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ApiResponse<Void>>
    handleNotFound(
            ResourceNotFoundException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ApiResponse<>(
                                false,
                                ex.getMessage(),
                                null
                        )
                );
    }

    @ExceptionHandler(
            BusinessException.class
    )
    public ResponseEntity<ApiResponse<Void>>
    handleBusinessException(
            BusinessException ex
    ) {

        return ResponseEntity
                .badRequest()
                .body(
                        new ApiResponse<>(
                                false,
                                ex.getMessage(),
                                null
                        )
                );
    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<ApiResponse<Void>>
    handleGenericException(
            Exception ex
    ) {

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(
                        new ApiResponse<>(
                                false,
                                "Unexpected error occurred",
                                null
                        )
                );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ApiResponse<String>>
    handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        String errorMessage =
                ex.getBindingResult()
                        .getFieldErrors()
                        .getFirst()
                        .getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(
                        new ApiResponse<>(
                                false,
                                errorMessage,
                                null
                        )
                );
    }

    @ExceptionHandler(
            RateLimitExceededException.class
    )
    public ResponseEntity<ApiResponse<Void>>
    handleRateLimitExceededException(
            RateLimitExceededException ex
    ) {

        return ResponseEntity
                .status(429)
                .body(
                        new ApiResponse<>(
                                false,
                                ex.getMessage(),
                                null
                        )
                );
    }
}
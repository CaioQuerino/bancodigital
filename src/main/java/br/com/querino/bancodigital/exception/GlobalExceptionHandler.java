package br.com.querino.bancodigital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.querino.bancodigital.dto.response.ApiResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
            .success(false)
            .message(exception.getMessage())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
            .findFirst()
            .map(fieldError -> fieldError.getDefaultMessage())
            .orElse("Requisição inválida");

        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
            .success(false)
            .message(message)
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
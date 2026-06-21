package br.com.querino.bancodigital.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.querino.bancodigital.dto.auth.AuthResponseDTO;
import br.com.querino.bancodigital.dto.auth.LoginRequestDTO;
import br.com.querino.bancodigital.service.AuthService;
import br.com.querino.bancodigital.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody LoginRequestDTO dto) {
        AuthResponseDTO authResponse = authService.login(dto);

        ApiResponse<AuthResponseDTO> response = ApiResponse.<AuthResponseDTO>builder()
            .success(true)
            .message("Login realizado com sucesso")
            .data(authResponse)
            .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
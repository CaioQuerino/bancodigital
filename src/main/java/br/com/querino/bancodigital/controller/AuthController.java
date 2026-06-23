package br.com.querino.bancodigital.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.querino.bancodigital.dto.auth.AuthResponseDTO;
import br.com.querino.bancodigital.dto.auth.LoginRequestDTO;
import br.com.querino.bancodigital.dto.response.ApiResponseDTO;
import br.com.querino.bancodigital.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(
    name = "Autenticação",
    description = "Operações de autenticação e geração de token JWT."
)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(
        summary = "Realizar login",
        description = "Autentica o usuário e retorna um token JWT."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Login realizado com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Credenciais inválidas"
        )
    })
    public ResponseEntity<ApiResponseDTO<AuthResponseDTO>> login(
            @Valid @RequestBody LoginRequestDTO dto) {

        AuthResponseDTO authResponse = authService.login(dto);

        ApiResponseDTO<AuthResponseDTO> response =
                ApiResponseDTO.<AuthResponseDTO>builder()
                        .success(true)
                        .message("Login realizado com sucesso")
                        .data(authResponse)
                        .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
package br.com.querino.bancodigital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.querino.bancodigital.dto.response.ApiResponseDTO;
import br.com.querino.bancodigital.dto.user.CreateUserDTO;
import br.com.querino.bancodigital.dto.user.ListUserDTO;
import br.com.querino.bancodigital.dto.user.UserResponseDTO;
import br.com.querino.bancodigital.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(
    name = "Usuários",
    description = "Operações relacionadas aos usuários do banco digital."
)
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('BANK_MANAGER','BANK_EMPLOYEE')")
    @Operation(
        summary = "Cadastrar usuário",
        description = "Realiza o cadastro de um novo usuário no banco digital.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Usuário criado com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso negado"
        ),
        @ApiResponse(
            responseCode = "409",
            description = "CPF ou e-mail já cadastrados"
        )
    })
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> createUser(
            @Valid @RequestBody CreateUserDTO dto) {

        UserResponseDTO savedUser =
                userService.createUser(dto);

        ApiResponseDTO<UserResponseDTO> response =
                ApiResponseDTO.<UserResponseDTO>builder()
                        .success(true)
                        .message("Usuário criado com sucesso")
                        .data(savedUser)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(
        summary = "Listar usuários",
        description = "Retorna todos os usuários cadastrados no sistema.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Usuários listados com sucesso"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso negado"
        )
    })
    public ResponseEntity<ApiResponseDTO<List<ListUserDTO>>> listUsers() {

        List<ListUserDTO> users =
                userService.listUsers();

        ApiResponseDTO<List<ListUserDTO>> response =
                ApiResponseDTO.<List<ListUserDTO>>builder()
                        .success(true)
                        .message("Usuários listados com sucesso")
                        .data(users)
                        .build();

        return ResponseEntity.ok(response);
    }
}
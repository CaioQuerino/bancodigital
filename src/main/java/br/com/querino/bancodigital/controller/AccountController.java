package br.com.querino.bancodigital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.querino.bancodigital.dto.account.AccountResponseDTO;
import br.com.querino.bancodigital.dto.account.CreateAccountDTO;
import br.com.querino.bancodigital.dto.account.CreateUserAccountDTO;
import br.com.querino.bancodigital.dto.account.ListAccountDTO;
import br.com.querino.bancodigital.dto.response.ApiResponseDTO;
import br.com.querino.bancodigital.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
@Tag(
    name = "Contas",
    description = "Operações relacionadas às contas bancárias."
)
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @PreAuthorize("hasAnyRole('BANK_MANAGER','BANK_EMPLOYEE')")
    @Operation(
        summary = "Criar conta",
        description = "Cria uma nova conta bancária para um usuário já existente.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Conta criada com sucesso"
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
            description = "Conta já existente"
        )
    })
    public ResponseEntity<ApiResponseDTO<AccountResponseDTO>> createAccount(
            @Valid @RequestBody CreateAccountDTO dto) {

        AccountResponseDTO savedAccount =
                accountService.createAccount(dto);

        ApiResponseDTO<AccountResponseDTO> response =
                ApiResponseDTO.<AccountResponseDTO>builder()
                        .success(true)
                        .message("Conta criada com sucesso")
                        .data(savedAccount)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('BANK_MANAGER','BANK_EMPLOYEE')")
    @Operation(
        summary = "Criar usuário e conta",
        description = "Realiza o cadastro de um novo usuário juntamente com sua conta bancária.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Usuário e conta criados com sucesso"
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
    public ResponseEntity<ApiResponseDTO<AccountResponseDTO>> createUserAccount(
            @Valid @RequestBody CreateUserAccountDTO dto) {

        AccountResponseDTO savedAccount =
                accountService.createUserAccount(
                        dto.getUser(),
                        dto.getAccount()
                );

        ApiResponseDTO<AccountResponseDTO> response =
                ApiResponseDTO.<AccountResponseDTO>builder()
                        .success(true)
                        .message("Conta criada com sucesso")
                        .data(savedAccount)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(
        summary = "Listar contas",
        description = "Retorna todas as contas cadastradas no sistema.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Contas listadas com sucesso"
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
    public ResponseEntity<ApiResponseDTO<List<ListAccountDTO>>> listAccounts() {

        List<ListAccountDTO> accounts =
                accountService.listAccounts();

        ApiResponseDTO<List<ListAccountDTO>> response =
                ApiResponseDTO.<List<ListAccountDTO>>builder()
                        .success(true)
                        .message("Contas listadas com sucesso")
                        .data(accounts)
                        .build();

        return ResponseEntity.ok(response);
    }
}
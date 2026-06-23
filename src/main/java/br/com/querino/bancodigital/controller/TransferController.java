package br.com.querino.bancodigital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.querino.bancodigital.dto.response.ApiResponseDTO;
import br.com.querino.bancodigital.dto.transfer.TransferDTO;
import br.com.querino.bancodigital.dto.transfer.TransferResponseDTO;
import br.com.querino.bancodigital.service.TransferService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transfers")
@AllArgsConstructor
@Tag(
    name = "Transferências",
    description = "Operações relacionadas às transferências bancárias."
)
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(
        summary = "Realizar transferência",
        description = "Realiza uma transferência entre contas bancárias.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Transferência realizada com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Conta não encontrada"
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Saldo insuficiente"
        )
    })
    public ResponseEntity<ApiResponseDTO<TransferResponseDTO>> createTransfer(
            @Valid @RequestBody TransferDTO dto) {

        TransferResponseDTO savedTransfer =
                transferService.createTransfer(dto);

        ApiResponseDTO<TransferResponseDTO> response =
                ApiResponseDTO.<TransferResponseDTO>builder()
                        .success(true)
                        .message("Transferência realizada com sucesso")
                        .data(savedTransfer)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(
        summary = "Listar transferências",
        description = "Retorna todas as transferências cadastradas.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Transferências listadas com sucesso"
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
    public ResponseEntity<ApiResponseDTO<List<TransferResponseDTO>>> listTransfers() {

        List<TransferResponseDTO> transfers =
                transferService.listTransfers();

        ApiResponseDTO<List<TransferResponseDTO>> response =
                ApiResponseDTO.<List<TransferResponseDTO>>builder()
                        .success(true)
                        .message("Transferências listadas com sucesso")
                        .data(transfers)
                        .build();

        return ResponseEntity.ok(response);
    }
}
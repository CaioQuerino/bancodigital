package br.com.querino.bancodigital.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.querino.bancodigital.dto.pix.CreatePixDTO;
import br.com.querino.bancodigital.dto.pix.PixResponseDTO;
import br.com.querino.bancodigital.dto.response.ApiResponseDTO;
import br.com.querino.bancodigital.entity.PixEntity;
import br.com.querino.bancodigital.service.PixService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pix")
@AllArgsConstructor
@Tag(
    name = "PIX",
    description = "Operações relacionadas às chaves PIX."
)
public class PixController {

    private final PixService pixService;

    @PostMapping
    @Operation(
        summary = "Cadastrar chave PIX",
        description = "Cria uma nova chave PIX para um usuário.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Chave PIX criada com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Chave PIX já cadastrada"
        )
    })
    public ResponseEntity<ApiResponseDTO<PixResponseDTO>> create(
            @Valid @RequestBody CreatePixDTO dto) {

        PixEntity pix = pixService.createKey(dto);

        PixResponseDTO responseDTO = PixResponseDTO.builder()
                .id(pix.getId().toString())
                .keyPix(pix.getKeyPix())
                .keyType(pix.getKeyType())
                .build();

        ApiResponseDTO<PixResponseDTO> response =
                ApiResponseDTO.<PixResponseDTO>builder()
                        .success(true)
                        .message("Chave PIX criada com sucesso")
                        .data(responseDTO)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
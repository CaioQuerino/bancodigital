package br.com.querino.bancodigital.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "AuthResponseDTO",
    description = "Resposta retornada após autenticação bem-sucedida."
)
public class AuthResponseDTO {

    @Schema(
        description = "Token JWT utilizado para autenticação das requisições protegidas.",
        example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYWlvQGVtYWlsLmNvbSIsImlhdCI6MTcxODk5MDAwMCwiZXhwIjoxNzE5MDc2NDAwfQ.xxxxxxxxxxxxxxxxxxxxxxxxx"
    )
    private String token;

    @Schema(
        description = "Tipo do token utilizado no cabeçalho Authorization.",
        example = "Bearer",
        allowableValues = {"Bearer"}
    )
    private String type;
}
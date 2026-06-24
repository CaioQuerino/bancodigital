package br.com.querino.bancodigital.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(
        description = """
                Tipos de chave PIX disponíveis:

                CPF - Cadastro de Pessoa Física
                EMAIL - Endereço de e-mail
                PHONE - Número de telefone
                RANDOM - Chave aleatória gerada pelo sistema
                """
)
public enum KeyType {

    @Schema(description = "CPF")
    CPF("CPF"),

    @Schema(description = "E-mail")
    EMAIL("E-mail"),

    @Schema(description = "Telefone")
    PHONE("Telefone"),

    @Schema(description = "Chave Aleatória")
    RANDOM("Chave Aleatória");

    private final String description;
}
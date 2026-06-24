package br.com.querino.bancodigital.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = """
                Tipos de conta disponíveis no Banco Digital:

                CORRENTE - Conta corrente tradicional
                POUPANCA - Conta destinada à poupança
                UNIVERSITARIA - Conta para estudantes universitários
                ESPECIAL - Conta com limite especial
                INVESTIMENTO - Conta voltada para investimentos
                SALARIO - Conta para recebimento de salário
                """
)
public enum AccountType {

    @Schema(description = "Conta Corrente")
    CORRENTE("Conta Corrente"),

    @Schema(description = "Conta Poupança")
    POUPANCA("Conta Poupança"),

    @Schema(description = "Conta Universitária")
    UNIVERSITARIA("Conta Universitária"),

    @Schema(description = "Conta Especial")
    ESPECIAL("Conta Especial"),

    @Schema(description = "Conta de Investimento")
    INVESTIMENTO("Conta de Investimento"),

    @Schema(description = "Conta Salário")
    SALARIO("Conta Salário");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static AccountType fromString(String text) {
        for (AccountType b : AccountType.values()) {
            if (b.name().equalsIgnoreCase(text) || b.description.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Tipo de conta inválido: " + text);
    }
}
package br.com.querino.bancodigital.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = """
                Perfis de acesso disponíveis no sistema:

                STUDENT - Cliente estudante
                AUTONOMOUS - Cliente autônomo
                MEI - Cliente MEI
                EMPRESARIO - Cliente empresário
                PUBLIC_SERVANT - Cliente servidor público
                BANK_EMPLOYEE - Funcionário do banco
                BANK_MANAGER - Gerente do banco
                """
)
public enum UserRole {

    @Schema(description = "Cliente estudante")
    STUDENT("Student"),

    @Schema(description = "Cliente autônomo")
    AUTONOMOUS("Autonomous"),

    @Schema(description = "Cliente MEI")
    MEI("Mei"),

    @Schema(description = "Cliente empresário")
    EMPRESARIO("Empresario"),

    @Schema(description = "Cliente servidor público")
    PUBLIC_SERVANT("Public Servant"),

    @Schema(description = "Funcionário do banco")
    BANK_EMPLOYEE("Bank Employee"),

    @Schema(description = "Gerente do banco")
    BANK_MANAGER("Bank Manager");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static UserRole fromString(String text) {
        for (UserRole role : UserRole.values()) {
            if (role.name().equalsIgnoreCase(text) || role.description.equalsIgnoreCase(text)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Role inválido: " + text);
    }
}
package br.com.querino.bancodigital.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = """
                Ocupações disponíveis para cadastro:

                STUDENT - Estudante
                AUTONOMOUS - Trabalhador autônomo
                MEI - Microempreendedor Individual
                EMPRESARIO - Empresário
                PUBLIC_SERVANT - Servidor Público
                """
)
public enum Occupation {

    @Schema(description = "Estudante")
    STUDENT("Student"),

    @Schema(description = "Trabalhador Autônomo")
    AUTONOMOUS("Autonomous"),

    @Schema(description = "Microempreendedor Individual")
    MEI("Mei"),

    @Schema(description = "Empresário")
    EMPRESARIO("Empresario"),

    @Schema(description = "Servidor Público")
    PUBLIC_SERVANT("Public Servant");

    private final String description;

    Occupation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Occupation fromString(String text) {
        for (Occupation b : Occupation.values()) {
            if (b.name().equalsIgnoreCase(text) || b.description.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Ocupação inválida: " + text);
    }
}
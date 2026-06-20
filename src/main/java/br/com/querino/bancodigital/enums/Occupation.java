package br.com.querino.bancodigital.enums;

public enum Occupation {
    STUDENT("Student"),
    AUTONOMOUS("Autonomous"),
    MEI("Mei"),
    EMPRESARIO("Empresario"),
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
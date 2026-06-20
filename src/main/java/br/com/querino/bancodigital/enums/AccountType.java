package br.com.querino.bancodigital.enums;

public enum AccountType {
    CORRENTE("Conta Corrente"),
    POUPANCA("Conta Poupança"),
    UNIVERSITARIA("Conta Universitária"),
    ESPECIAL("Conta Especial"),
    INVESTIMENTO("Conta de Investimento"),
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
package br.com.querino.bancodigital.enums;

public enum UserRole {
    STUDENT("Student"),
    AUTONOMOUS("Autonomous"),
    MEI("Mei"),
    EMPRESARIO("Empresario"),
    PUBLIC_SERVANT("Public Servant"),
    BANK_EMPLOYEE("Bank Employee"),
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
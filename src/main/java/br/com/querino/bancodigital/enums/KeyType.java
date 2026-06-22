package br.com.querino.bancodigital.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KeyType {

    CPF("CPF"),
    EMAIL("E-mail"),
    PHONE("Telefone"),
    RANDOM("Chave Aleatória");

    private final String description;
}
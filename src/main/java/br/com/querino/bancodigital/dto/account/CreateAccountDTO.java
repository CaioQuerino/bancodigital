package br.com.querino.bancodigital.dto.account;

import java.util.UUID;

import br.com.querino.bancodigital.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {
    private UUID userId;
    private Double balance;
    private Double creditLimit;
    private AccountType accountType;
}
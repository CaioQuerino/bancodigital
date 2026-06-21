package br.com.querino.bancodigital.dto.account;

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
    private Double balance;
    private Double creditLimit;
    private AccountType accountType;
}
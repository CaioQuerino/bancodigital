package br.com.querino.bancodigital.dto;

import br.com.querino.bancodigital.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String accountNumber;
    private String agency;
    private Double balance;
    private Double limit;
    private AccountType accountType;
}
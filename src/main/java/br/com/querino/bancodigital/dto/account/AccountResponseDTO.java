package br.com.querino.bancodigital.dto.account;

import br.com.querino.bancodigital.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de resposta da conta bancária")
public class AccountResponseDTO {

    @Schema(
        description = "Número da conta",
        example = "1234567890"
    )
    private String accountNumber;

    @Schema(
        description = "Número da agência",
        example = "5321"
    )
    private String agency;

    @Schema(
        description = "Saldo disponível da conta",
        example = "1200.50"
    )
    private Double balance;

    @Schema(
        description = "Limite de crédito disponível",
        example = "300.00"
    )
    private Double creditLimit;

    @Schema(
        description = "Tipo da conta",
        example = "CORRENTE",
        allowableValues = {
            "CORRENTE",
            "POUPANCA",
            "SALARIO"
        }
    )
    private AccountType accountType;
}
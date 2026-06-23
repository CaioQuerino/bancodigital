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
@Schema(
    name = "ListAccountDTO",
    description = "Dados de retorno de uma conta bancária"
)
public class ListAccountDTO {

    @Schema(
        description = "Número único da conta bancária",
        example = "1234567890",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String accountNumber;

    @Schema(
        description = "Número da agência da conta",
        example = "5321",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String agency;

    @Schema(
        description = "Saldo disponível para movimentação",
        example = "1200.50",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Double balance;

    @Schema(
        description = "Limite de crédito disponível para a conta",
        example = "3000.00",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Double creditLimit;

    @Schema(
        description = """
            Tipo da conta bancária.
            
            CORRENTE - Conta para movimentações diárias.
            POUPANCA - Conta destinada a poupança e rendimento.
            SALARIO - Conta destinada ao recebimento de salário.
            """,
        example = "CORRENTE",
        allowableValues = {
            "CORRENTE",
            "POUPANCA",
            "SALARIO"
        },
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private AccountType accountType;
}
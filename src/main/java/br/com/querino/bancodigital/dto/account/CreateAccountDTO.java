package br.com.querino.bancodigital.dto.account;

import java.util.UUID;

import br.com.querino.bancodigital.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de uma conta bancária")
public class CreateAccountDTO {

    @Schema(
        description = "Identificador do usuário proprietário da conta",
        example = "2a44cfef-59cd-4458-9dbd-5b37b4b59706"
    )
    @NotNull(message = "O usuário é obrigatório")
    private UUID userId;

    @Schema(
        description = "Saldo inicial da conta",
        example = "1200.00"
    )
    @NotNull(message = "O saldo é obrigatório")
    private Double balance;

    @Schema(
        description = "Limite de crédito da conta",
        example = "500.00"
    )
    @NotNull(message = "O limite de crédito é obrigatório")
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
    @NotNull(message = "O tipo da conta é obrigatório")
    private AccountType accountType;
}
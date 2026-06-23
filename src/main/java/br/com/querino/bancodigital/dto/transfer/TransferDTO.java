package br.com.querino.bancodigital.dto.transfer;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para realização de uma transferência bancária")
public class TransferDTO {

    @Schema(
        description = "Número da conta de origem",
        example = "1234567890"
    )
    @NotBlank(message = "Número da conta de origem é obrigatório")
    private String sourceAccountNumber;

    @Schema(
        description = "Número da conta de destino",
        example = "0987654321"
    )
    @NotBlank(message = "Número da conta de destino é obrigatório")
    private String destinationAccountNumber;

    @Schema(
        description = "Descrição da transferência",
        example = "Pagamento da mensalidade da faculdade"
    )
    private String description;

    @Schema(
        description = "Data da transferência no formato yyyy-MM-dd",
        example = "2026-06-22T14:30:00"
    )
    @NotBlank(message = "Data da transferência é obrigatória")
    private String transferDate;

    @Schema(
        description = "Valor da transferência",
        example = "250.50"
    )
    @NotNull(message = "Valor da transferência é obrigatório")
    private Double amount;
}
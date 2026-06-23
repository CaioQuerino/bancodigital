package br.com.querino.bancodigital.dto.transfer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "TransferResponseDTO",
    description = "Dados retornados após uma transferência bancária realizada com sucesso."
)
public class TransferResponseDTO {

    @Schema(
        description = "Identificador único da transferência.",
        example = "7d7a6b0f-9d5d-4b88-bd71-f8a9e2d6c123"
    )
    private String id;

    @Schema(
        description = "Número da conta de origem da transferência.",
        example = "1234567890"
    )
    private String sourceAccountNumber;

    @Schema(
        description = "Número da conta de destino da transferência.",
        example = "9876543210"
    )
    private String destinationAccountNumber;

    @Schema(
        description = "Descrição ou observação informada pelo usuário.",
        example = "Pagamento de aluguel"
    )
    private String description;

    @Schema(
        description = "Data e hora em que a transferência foi realizada.",
        example = "2026-06-22T14:30:00"
    )
    private String transferDate;

    @Schema(
        description = "Valor transferido.",
        example = "500.00"
    )
    private Double amount;
}
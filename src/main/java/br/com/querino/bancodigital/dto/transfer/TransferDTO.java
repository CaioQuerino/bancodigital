package br.com.querino.bancodigital.dto.transfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {
    @NotBlank(message = "Número da conta de origem é obrigatório")
    private String sourceAccountNumber;

    @NotBlank(message = "Número da conta de destino é obrigatório")
    private String destinationAccountNumber;

    private String description;

    @NotBlank(message = "Data da transferência é obrigatória")
    private String transferDate; 

    @NotNull(message = "Valor da transferência é obrigatório")
    private Double amount;
}
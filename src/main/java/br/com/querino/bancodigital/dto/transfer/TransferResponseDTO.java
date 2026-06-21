package br.com.querino.bancodigital.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {
    private String id;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private String description;
    private String transferDate;
    private Double amount;
}

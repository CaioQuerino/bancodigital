package br.com.querino.bancodigital.dto.pix;

import br.com.querino.bancodigital.enums.KeyType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePixDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String keyPix;

    @NotBlank
    private KeyType keyType;
}
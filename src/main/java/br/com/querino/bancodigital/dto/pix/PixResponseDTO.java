package br.com.querino.bancodigital.dto.pix;

import br.com.querino.bancodigital.enums.KeyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
    name = "PixResponseDTO",
    description = "Dados da chave Pix cadastrada."
)
public class PixResponseDTO {

    @Schema(
        description = "Identificador da chave Pix.",
        example = "2a44cfef-59cd-4458-9dbd-5b37b4b59706"
    )
    private String id;

    @Schema(
        description = "Valor da chave Pix.",
        example = "fernando@email.com"
    )
    private String keyPix;

    @Schema(
        description = "Tipo da chave Pix.",
        example = "EMAIL"
    )
    private KeyType keyType;
}
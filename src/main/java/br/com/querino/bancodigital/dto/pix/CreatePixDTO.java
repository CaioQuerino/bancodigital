package br.com.querino.bancodigital.dto.pix;

import br.com.querino.bancodigital.enums.KeyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
    name = "CreatePixDTO",
    description = "Dados necessários para cadastro de uma chave Pix."
)
public class CreatePixDTO {

    @Schema(
        description = "Identificador único do usuário proprietário da chave Pix.",
        example = "2a44cfef-59cd-4458-9dbd-5b37b4b59706",
        format = "uuid",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "O ID do usuário é obrigatório")
    private String userId;

    @Schema(
        description = """
            Valor da chave Pix.
            
            Exemplos:
            - CPF: 12345678901
            - Telefone: 21999998888
            - E-mail: fernando@email.com
            - Aleatória: c8c6e1f2-80bc-4b4d-ae9b-7f7ab9b6c201
            """,
        example = "fernando@email.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "A chave Pix é obrigatória")
    private String keyPix;

    @Schema(
        description = """
            Tipo da chave Pix.
            
            CPF     - Cadastro por CPF.
            EMAIL   - Cadastro por e-mail.
            PHONE   - Cadastro por telefone.
            RANDOM  - Chave aleatória gerada pelo sistema.
            """,
        example = "EMAIL",
        allowableValues = {
            "CPF",
            "EMAIL",
            "PHONE",
            "RANDOM"
        },
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "O tipo da chave Pix é obrigatório")
    private KeyType keyType;
}
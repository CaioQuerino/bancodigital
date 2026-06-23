package br.com.querino.bancodigital.dto.user;

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
    name = "AddressDTO",
    description = "Dados de endereço retornados pela integração com o ViaCEP."
)
public class AddressDTO {

    @Schema(
        description = "CEP do endereço.",
        example = "21750-410"
    )
    private String cep;

    @Schema(
        description = "Nome da rua, avenida ou logradouro.",
        example = "Rua General Azeredo"
    )
    private String logradouro;

    @Schema(
        description = "Complemento do endereço.",
        example = "Apartamento 101"
    )
    private String complemento;

    @Schema(
        description = "Bairro do endereço.",
        example = "Realengo"
    )
    private String bairro;

    @Schema(
        description = "Cidade do endereço.",
        example = "Rio de Janeiro"
    )
    private String localidade;

    @Schema(
        description = "Unidade Federativa (UF).",
        example = "RJ"
    )
    private String uf;
}
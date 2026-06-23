package br.com.querino.bancodigital.dto.user;

import br.com.querino.bancodigital.enums.Occupation;
import br.com.querino.bancodigital.util.DataMaskUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
    name = "UserResponseDTO",
    description = "Dados do usuário retornados pela API com informações sensíveis mascaradas."
)
public class UserResponseDTO {

    @Schema(
        description = "Nome do usuário.",
        example = "Fernando"
    )
    private String firstName;

    @Schema(
        description = "Sobrenome do usuário.",
        example = "Hernandes"
    )
    private String lastName;

    @Schema(
        description = "CPF mascarado para proteção de dados.",
        example = "***.***.789-11"
    )
    private String cpf;

    @Schema(
        description = "Idade do usuário.",
        example = "23"
    )
    private Integer age;

    @Schema(
        description = "E-mail mascarado para proteção de dados.",
        example = "fer*****@email.com"
    )
    private String email;

    @Schema(
        description = "Telefone mascarado para proteção de dados.",
        example = "(21) *****-8888"
    )
    private String phone;

    @Schema(
        description = "Ocupação profissional do usuário.",
        example = "EMPRESARIO"
    )
    private Occupation occupation;

    @Schema(
        description = "Renda mensal declarada.",
        example = "4500.00"
    )
    private Double income;

    @Schema(
        description = "Endereço do usuário."
    )
    private AddressDTO address;

    public String getCpf() {
        return DataMaskUtil.maskCpf(cpf);
    }

    public String getPhone() {
        return DataMaskUtil.maskPhone(phone);
    }

    public String getEmail() {
        return DataMaskUtil.maskEmail(email);
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cpf='" + DataMaskUtil.maskCpf(cpf) + '\'' +
                ", age=" + age +
                ", email='" + DataMaskUtil.maskEmail(email) + '\'' +
                ", phone='" + DataMaskUtil.maskPhone(phone) + '\'' +
                ", occupation=" + occupation +
                ", income=" + income +
                ", address=" + address +
                '}';
    }
}
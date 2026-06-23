package br.com.querino.bancodigital.dto.user;

import br.com.querino.bancodigital.enums.Occupation;
import br.com.querino.bancodigital.util.DataMaskUtil;
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
    name = "ListUserDTO",
    description = "Dados do usuário retornados pela API."
)
public class ListUserDTO {

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

    public String getPhone() {
        return DataMaskUtil.maskPhone(phone);
    }

    public String getEmail() {
        return DataMaskUtil.maskEmail(email);
    }

    @Override
    public String toString() {
        return "ListUserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + DataMaskUtil.maskEmail(this.email) + '\'' +
                ", phone='" + DataMaskUtil.maskPhone(this.phone) + '\'' +
                ", occupation=" + occupation +
                ", income=" + income +
                ", address=" + address +
                '}';
    }
}
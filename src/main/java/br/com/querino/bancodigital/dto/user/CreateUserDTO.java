package br.com.querino.bancodigital.dto.user;

import br.com.querino.bancodigital.enums.Occupation;
import br.com.querino.bancodigital.enums.UserRole;
import br.com.querino.bancodigital.util.DataMaskUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "CreateUserDTO",
    description = "Dados necessários para cadastro de um usuário."
)
public class CreateUserDTO {

    @Schema(
        description = "Nome do usuário.",
        example = "Fernando"
    )
    @NotNull(message = "Nome é obrigatório")
    private String firstName;

    @Schema(
        description = "Sobrenome do usuário.",
        example = "Hernandes"
    )
    @NotNull(message = "Sobrenome é obrigatório")
    private String lastName;

    @Schema(
        description = "CPF contendo 11 dígitos.",
        example = "12345678911"
    )
    @Pattern(
        regexp = "\\d{11}",
        message = "CPF deve conter 11 dígitos numéricos"
    )
    private String cpf;

    @Schema(
        description = "Idade do usuário.",
        example = "23"
    )
    @Min(value = 18, message = "O usuário deve ter pelo menos 18 anos")
    private Integer age;

    @Schema(
        description = "E-mail do usuário.",
        example = "fernando@email.com"
    )
    @Email(message = "Email deve ser válido")
    private String email;

    @Schema(
        description = "Perfil de acesso do usuário.",
        example = "CLIENT",
        allowableValues = {
            "CLIENT",
            "BANK_EMPLOYEE",
            "ADMIN"
        }
    )
    private UserRole userRole;

    @Schema(
        description = "Senha de acesso.",
        example = "SenhaDificil5321@",
        format = "password"
    )
    private String password;

    @Schema(
        description = "Telefone do usuário.",
        example = "21999998888"
    )
    private String phone;

    @Schema(
        description = "Ocupação profissional.",
        example = "EMPRESARIO"
    )
    private Occupation occupation;

    @Schema(
        description = "Renda mensal declarada.",
        example = "4500.00"
    )
    private Double income;

    @Valid
    @Schema(
        description = "Endereço obtido através da integração com ViaCEP."
    )
    private AddressDTO address;

    @Override
    public String toString() {
         return "CreateUserDTO{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", userRole='" + userRole + '\'' +
                    ", cpf='" + DataMaskUtil.maskCpf(cpf) + '\'' +
                    ", age='" + age + '\'' +
                    ", email='" + DataMaskUtil.maskEmail(email) + '\'' +
                    ", phone='" + DataMaskUtil.maskPhone(phone) + '\'' +
                    ", occupation=" + occupation +
                    ", income=" + income +
                    ", address=" + address +
                    '}';
    }
}
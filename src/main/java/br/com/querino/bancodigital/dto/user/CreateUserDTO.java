package br.com.querino.bancodigital.dto.user;

import br.com.querino.bancodigital.enums.Occupation;
import br.com.querino.bancodigital.enums.UserRole;
import br.com.querino.bancodigital.util.DataMaskUtil;
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
public class CreateUserDTO {
    @NotNull(message = "Nome é obrigatório")
    private String firstName;

    @NotNull(message = "Sobrenome é obrigatório")
    private String lastName;

    @NotNull(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
    private String cpf;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 18, message = "O usuário deve ter pelo menos 18 anos")
    private Integer age;

    @NotNull(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    private UserRole userRole;

    @NotNull(message = "Senha é obrigatória")
    private String password;

    @NotNull(message = "Telefone é obrigatório")
    private String phone;

    @NotNull(message = "Ocupação é obrigatória")
    private Occupation occupation;

    @NotNull(message = "Renda é obrigatória")
    private Double income;

    @Valid
    @NotNull(message = "Endereço é obrigatório")
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
                    ", phone='" + phone + '\'' +
                    ", occupation=" + occupation +
                    ", income=" + income +
                    ", address=" + address +
                    '}';
    }
}
package br.com.querino.bancodigital.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "LoginRequestDTO",
    description = "Dados necessários para autenticação do usuário."
)
public class LoginRequestDTO {

    @Schema(
        description = "E-mail cadastrado do usuário.",
        example = "fernando@email.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @Schema(
        description = "Senha de acesso da conta.",
        example = "SenhaDificil5321@",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Senha é obrigatória")
    private String password;
}
package br.com.querino.bancodigital.dto.account;

import br.com.querino.bancodigital.dto.user.CreateUserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "CreateUserAccountDTO",
    description = "DTO responsável pelo cadastro simultâneo de usuário e conta bancária."
)
public class CreateUserAccountDTO {

    @Valid
    @NotNull(message = "Dados do usuário são obrigatórios")
    @Schema(
        description = "Informações do titular da conta.",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private CreateUserDTO user;

    @Valid
    @NotNull(message = "Dados da conta são obrigatórios")
    @Schema(
        description = "Informações da conta bancária a ser criada.",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private CreateAccountDTO account;
}
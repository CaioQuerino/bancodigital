package br.com.querino.bancodigital.dto.account;

import br.com.querino.bancodigital.dto.CreateUserDTO;
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
public class CreateUserAccountDTO {
    @Valid
    @NotNull(message = "Dados do usuário são obrigatórios")
    private CreateUserDTO user;

    @Valid
    @NotNull(message = "Dados da conta são obrigatórios")
    private CreateAccountDTO account;
}
package br.com.querino.bancodigital.dto.user;

import br.com.querino.bancodigital.enums.Occupation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String cpf;
    private Integer age;
    private String email;
    private String phone;
    private Occupation occupation;
    private Double income;
    private AddressDTO address;
}
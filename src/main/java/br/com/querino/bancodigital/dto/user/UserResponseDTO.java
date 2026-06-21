package br.com.querino.bancodigital.dto.user;

import br.com.querino.bancodigital.enums.Occupation;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.querino.bancodigital.util.DataMaskUtil;
@Data
@NoArgsConstructor
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
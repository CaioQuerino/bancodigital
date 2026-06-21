package br.com.querino.bancodigital.dto.user;

import br.com.querino.bancodigital.enums.Occupation;
import br.com.querino.bancodigital.util.DataMaskUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Occupation occupation;
    private Double income;
    private AddressDTO address;

    @Override
    public String toString() {
         return "ListUserDTO{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + DataMaskUtil.maskEmail(this.email) + '\'' +
                    ", phone='" + phone + '\'' +
                    ", occupation=" + occupation +
                    ", income=" + income +
                    ", address=" + address +
                    '}';
    }
}
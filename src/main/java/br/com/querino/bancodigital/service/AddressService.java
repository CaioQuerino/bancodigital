package br.com.querino.bancodigital.service;

import org.springframework.stereotype.Service;

import br.com.querino.bancodigital.dto.AddressDTO;
import br.com.querino.bancodigital.entity.AddressEntity;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressService {
    public AddressEntity convertDtoToEntity(AddressDTO dto) {
        return AddressEntity.builder()
            .cep(dto.getCep())
            .logradouro(dto.getLogradouro())
            .complemento(dto.getComplemento())
            .bairro(dto.getBairro())
            .localidade(dto.getLocalidade())
            .uf(dto.getUf())
            .build();
    }

    public AddressDTO convertEntityToDto(AddressEntity entity) {
        return AddressDTO.builder()
            .cep(entity.getCep())
            .logradouro(entity.getLogradouro())
            .complemento(entity.getComplemento())
            .bairro(entity.getBairro())
            .localidade(entity.getLocalidade())
            .uf(entity.getUf())
            .build();
    }
}

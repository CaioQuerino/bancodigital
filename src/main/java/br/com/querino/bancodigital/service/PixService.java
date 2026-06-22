package br.com.querino.bancodigital.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.querino.bancodigital.dto.pix.CreatePixDTO;
import br.com.querino.bancodigital.entity.PixEntity;
import br.com.querino.bancodigital.entity.UserEntity;
import br.com.querino.bancodigital.repository.PixRepository;
import br.com.querino.bancodigital.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PixService {

    private final UserRepository userRepository;
    private final PixRepository pixRepository;

    public PixEntity createKey(CreatePixDTO dto) {

        if (pixRepository.existsByKeyPix(dto.getKeyPix())) {
            throw new IllegalArgumentException(
                "Esta chave Pix já está cadastrada"
            );
        }

        UserEntity user = userRepository.findById(
                UUID.fromString(dto.getUserId()))
            .orElseThrow(() ->
                new IllegalArgumentException("Usuário não encontrado"));

        PixEntity pix = PixEntity.builder()
            .keyPix(dto.getKeyPix())
            .keyType(dto.getKeyType())
            .user(user)
            .amount(0.0)
            .description("Cadastro de chave Pix")
            .build();

        return pixRepository.save(pix);
    }
}
package br.com.querino.bancodigital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.querino.bancodigital.entity.AddressEntity;
import br.com.querino.bancodigital.util.Convert;
import br.com.querino.bancodigital.util.DataMaskUtil;
import br.com.querino.bancodigital.dto.user.CreateUserDTO;
import br.com.querino.bancodigital.dto.user.ListUserDTO;
import br.com.querino.bancodigital.dto.user.UserResponseDTO;
import br.com.querino.bancodigital.entity.UserEntity;
import br.com.querino.bancodigital.repository.UserRepository;
import br.com.querino.bancodigital.enums.UserRole;
import lombok.AllArgsConstructor;

/**
 * Serviço responsável pelas operações relacionadas aos usuários.
 */
@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private AddressService addressService;
    private PasswordEncoder passwordEncoder;

    /**
     * Cria um novo usuário no sistema.
     *
     * @param dto dados do usuário
     * @return usuário criado
     * @throws IllegalArgumentException quando CPF, e-mail ou telefone já estiverem cadastrados
     */
    public UserResponseDTO createUser(CreateUserDTO dto) {
        UserEntity entity = Convert.to(dto, UserEntity::new);

        if (userRepository.existsByCpf(entity.getCpf())) {
            throw new IllegalArgumentException("CPF Já cadastrado");
        }

        if(userRepository.existsByEmail(entity.getEmail())) {
            throw new IllegalArgumentException("Email Já cadastrado");
        }

        if(userRepository.existsByPhone(entity.getPhone())) {
            throw new IllegalArgumentException("Telefone Já cadastrado");
        }

        if(entity.getUserRole() == null) {
            entity.setUserRole(UserRole.AUTONOMOUS);
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        
        AddressEntity address = addressService.convertDtoToEntity(dto.getAddress());
        if (address != null) {
            address.setUser(entity);
        }
        entity.setAddress(address);
        UserEntity savedEntity = userRepository.save(entity);

        UserResponseDTO response = Convert.to(savedEntity, UserResponseDTO::new);
        response.setAddress(addressService.convertEntityToDto(savedEntity.getAddress()));
        return response;

    }

    /**
     * Lista todos os usuários cadastrados.
     *
     * @return lista de usuários
     */
    public List<ListUserDTO> listUsers() {
        return userRepository.findAll()
            .stream()
            .map(user -> ListUserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(DataMaskUtil.maskEmail(user.getEmail()))
                .phone(DataMaskUtil.maskPhone(user.getPhone()))
                .occupation(user.getOccupation())
                .income(user.getIncome())
                .address(addressService.convertEntityToDto(user.getAddress()))
                .build())
            .collect(Collectors.toList());
    }
}
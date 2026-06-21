package br.com.querino.bancodigital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.querino.bancodigital.util.DataMaskUtil;
import br.com.querino.bancodigital.dto.user.CreateUserDTO;
import br.com.querino.bancodigital.dto.user.ListUserDTO;
import br.com.querino.bancodigital.dto.user.UserResponseDTO;
import br.com.querino.bancodigital.entity.UserEntity;
import br.com.querino.bancodigital.repository.UserRepository;
import br.com.querino.bancodigital.enums.UserRole;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private AddressService addressService;
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(CreateUserDTO dto) {
        UserEntity entity = convertDtoToEntity(dto);
        
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        UserEntity savedEntity = userRepository.save(entity);

        return convertEntityToDto(savedEntity);
    }

    public List<ListUserDTO> listUsers() {
        return userRepository.findAll()
            .stream()
            .map(this::convertEntityToListUserDto)
            .collect(Collectors.toList());
    }

    private UserEntity convertDtoToEntity(CreateUserDTO dto) {
        return UserEntity.builder()
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .userRole(UserRole.AUTONOMOUS)
            .cpf(dto.getCpf())
            .age(dto.getAge())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .phone(dto.getPhone())
            .occupation(dto.getOccupation())
            .income(dto.getIncome())
            .address(addressService.convertDtoToEntity(dto.getAddress()))
            .build();
    }

    private UserResponseDTO convertEntityToDto(UserEntity entity) {
        return UserResponseDTO.builder()
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .cpf(DataMaskUtil.maskCpf(entity.getCpf()))
            .age(entity.getAge())
            .email(DataMaskUtil.maskEmail(entity.getEmail()))
            .phone(entity.getPhone())
            .occupation(entity.getOccupation())
            .income(entity.getIncome())
            .address(addressService.convertEntityToDto(entity.getAddress()))
            .build();
    }

    private ListUserDTO convertEntityToListUserDto(UserEntity entity) {
        return ListUserDTO.builder()
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .email(DataMaskUtil.maskEmail(entity.getEmail()))
            .phone(entity.getPhone())
            .occupation(entity.getOccupation())
            .income(entity.getIncome())
            .address(addressService.convertEntityToDto(entity.getAddress()))
            .build();
    }
}
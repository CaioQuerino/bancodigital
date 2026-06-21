package br.com.querino.bancodigital.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.querino.bancodigital.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Optional<UserEntity> findByEmail(String email);
}
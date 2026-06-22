package br.com.querino.bancodigital.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.querino.bancodigital.entity.PixEntity;

public interface PixRepository extends JpaRepository<PixEntity, UUID> {
    Optional<PixEntity> findByKeyPix(String keyPix);
    boolean existsByKeyPix(String keyPix);
}
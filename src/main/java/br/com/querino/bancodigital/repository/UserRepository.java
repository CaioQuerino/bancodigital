package br.com.querino.bancodigital.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.querino.bancodigital.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {}

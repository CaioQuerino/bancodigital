package br.com.querino.bancodigital.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.querino.bancodigital.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity,UUID> {
	Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
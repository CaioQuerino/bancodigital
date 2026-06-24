package br.com.querino.bancodigital.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.querino.bancodigital.entity.AccountEntity;

/**
 * Repositório responsável pelas operações de persistência de contas bancárias.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    /**
     * Busca uma conta pelo número da conta.
     *
     * @param accountNumber número da conta
     * @return conta encontrada, caso exista
     */
    Optional<AccountEntity> findByAccountNumber(String accountNumber);

}
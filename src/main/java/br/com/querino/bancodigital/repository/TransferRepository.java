package br.com.querino.bancodigital.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.querino.bancodigital.entity.Transfer;

/**
 * Repositório responsável pelas operações de persistência
 * das transferências bancárias.
 */
public interface TransferRepository extends JpaRepository<Transfer, UUID> {}

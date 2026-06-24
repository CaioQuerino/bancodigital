package br.com.querino.bancodigital.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.querino.bancodigital.entity.PixEntity;

/**
 * Repositório responsável pelas operações de persistência
 * relacionadas às chaves PIX.
 */
public interface PixRepository extends JpaRepository<PixEntity, UUID> {

    /**
     * Busca uma chave PIX pelo valor informado.
     *
     * @param keyPix valor da chave PIX
     * @return chave encontrada, caso exista
     */
    Optional<PixEntity> findByKeyPix(String keyPix);

    /**
     * Verifica se uma chave PIX já está cadastrada.
     *
     * @param keyPix valor da chave PIX
     * @return true se existir, false caso contrário
     */
    boolean existsByKeyPix(String keyPix);
}
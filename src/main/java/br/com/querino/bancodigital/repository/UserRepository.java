package br.com.querino.bancodigital.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.querino.bancodigital.entity.UserEntity;

/**
 * Repositório responsável pelas operações de persistência dos usuários.
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * Verifica se já existe um usuário com o CPF informado.
     *
     * @param cpf CPF do usuário
     * @return true se existir
     */
    boolean existsByCpf(String cpf);

    /**
     * Verifica se já existe um usuário com o e-mail informado.
     *
     * @param email e-mail do usuário
     * @return true se existir
     */
    boolean existsByEmail(String email);

    /**
     * Verifica se já existe um usuário com o telefone informado.
     *
     * @param phone telefone do usuário
     * @return true se existir
     */
    boolean existsByPhone(String phone);

    /**
     * Busca um usuário pelo e-mail.
     *
     * @param email e-mail do usuário
     * @return usuário encontrado
     */
    Optional<UserEntity> findByEmail(String email);
}
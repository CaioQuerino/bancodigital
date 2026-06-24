package br.com.querino.bancodigital.entity;

import br.com.querino.bancodigital.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Conta",
        description = "Entidade responsável por representar uma conta bancária"
)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(
            description = "Identificador único da conta",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    @Column(name = "account_number", nullable = false, unique = true)
    @Schema(
            description = "Número da conta",
            example = "123456-7"
    )
    private String accountNumber;

    @Column(nullable = false)
    @Schema(
            description = "Agência da conta",
            example = "0001"
    )
    private String agency;

    @Column(nullable = false)
    @Schema(
            description = "Saldo atual da conta",
            example = "1500.75"
    )
    private Double balance;

    @Column(name = "credit_limit")
    @Schema(
            description = "Limite de crédito disponível",
            example = "5000.00"
    )
    private Double creditLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    @Schema(
            description = "Tipo da conta",
            example = "CHECKING",
            implementation = AccountType.class
    )
    private AccountType accountType;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Schema(
            description = "Titular da conta"
    )
    private UserEntity user;
}
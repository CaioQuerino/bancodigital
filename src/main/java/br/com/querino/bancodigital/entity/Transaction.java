package br.com.querino.bancodigital.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Schema(
        name = "Transação",
        description = "Classe base para operações financeiras do sistema"
)
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(
            description = "Identificador único da transação",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    @Column(nullable = false)
    @Schema(
            description = "Valor monetário da transação",
            example = "250.75",
            minimum = "0"
    )
    private Double amount;

    @Column(length = 255)
    @Schema(
            description = "Descrição da transação",
            example = "Transferência para conta corrente"
    )
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(
            description = "Data e hora de criação da transação",
            example = "2026-06-23T14:30:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;
}
package br.com.querino.bancodigital.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Transferência",
        description = "Entidade responsável por representar transferências entre contas bancárias"
)
public class Transfer extends Transaction {

    @ManyToOne
    @JoinColumn(name = "origin_account_id", nullable = false)
    @Schema(
            description = "Conta de origem da transferência",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private AccountEntity originAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id", nullable = false)
    @Schema(
            description = "Conta de destino da transferência",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private AccountEntity destinationAccount;
}
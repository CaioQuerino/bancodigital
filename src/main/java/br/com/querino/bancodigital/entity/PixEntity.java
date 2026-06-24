package br.com.querino.bancodigital.entity;

import br.com.querino.bancodigital.enums.KeyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "pix_keys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(
        name = "Chave PIX",
        description = "Entidade responsável por representar uma chave PIX vinculada a um usuário"
)
public class PixEntity extends Transaction {

    @Schema(
            description = "Valor da chave PIX",
            example = "caio@email.com"
    )
    @Column(name = "key_pix", nullable = false, unique = true)
    private String keyPix;

    @Schema(
            description = "Tipo da chave PIX",
            example = "EMAIL",
            implementation = KeyType.class
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "key_type", nullable = false)
    private KeyType keyType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(
            description = "Usuário proprietário da chave PIX",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private UserEntity user;
}
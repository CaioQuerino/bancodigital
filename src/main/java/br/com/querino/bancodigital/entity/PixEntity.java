package br.com.querino.bancodigital.entity;

import br.com.querino.bancodigital.enums.KeyType;
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
public class PixEntity extends Transaction {

    @Column(name = "key_pix", nullable = false, unique = true)
    private String keyPix;

    @Enumerated(EnumType.STRING)
    @Column(name = "key_type", nullable = false)
    private KeyType keyType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
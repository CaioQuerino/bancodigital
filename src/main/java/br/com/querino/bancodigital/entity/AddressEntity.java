package br.com.querino.bancodigital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "addresses")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Schema(
        name = "Endereço",
        description = "Entidade responsável por armazenar os dados de endereço do usuário"
)
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(
            description = "Identificador único do endereço",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    @Schema(
            description = "CEP do endereço",
            example = "01001-000"
    )
    private String cep;

    @Schema(
            description = "Logradouro",
            example = "Praça da Sé"
    )
    private String logradouro;

    @Schema(
            description = "Complemento do endereço",
            example = "Apartamento 101"
    )
    private String complemento;

    @Schema(
            description = "Bairro",
            example = "Sé"
    )
    private String bairro;

    @Schema(
            description = "Cidade",
            example = "São Paulo"
    )
    private String localidade;

    @Schema(
            description = "Unidade Federativa (UF)",
            example = "SP"
    )
    private String uf;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Schema(
            description = "Usuário proprietário do endereço",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private UserEntity user;
}
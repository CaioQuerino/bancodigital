package br.com.querino.bancodigital.entity;

import br.com.querino.bancodigital.enums.Occupation;
import br.com.querino.bancodigital.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Schema(
        name = "Usuário",
        description = "Entidade responsável por representar os usuários do sistema bancário"
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(
            description = "Identificador único do usuário",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    @Column(name = "first_name", nullable = false)
    @Schema(
            description = "Primeiro nome do usuário",
            example = "Caio"
    )
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Schema(
            description = "Sobrenome do usuário",
            example = "Marques"
    )
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    @Schema(
            description = "Perfil de acesso do usuário",
            example = "CLIENT",
            implementation = UserRole.class
    )
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(
            description = "Ocupação profissional do usuário",
            example = "EMPLOYEE",
            implementation = Occupation.class
    )
    private Occupation occupation;

    @Schema(
            description = "Renda mensal do usuário",
            example = "8500.00"
    )
    private Double income;

    @Column(unique = true)
    @Schema(
            description = "CPF do usuário",
            example = "12345678901"
    )
    private String cpf;

    @Schema(
            description = "Idade do usuário",
            example = "30"
    )
    private Integer age;

    @Column(unique = true)
    @Schema(
            description = "E-mail do usuário",
            example = "caio@email.com"
    )
    private String email;

    @Schema(
            description = "Senha criptografada do usuário",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String password;

    @Column(unique = true)
    @Schema(
            description = "Telefone do usuário",
            example = "21999999999"
    )
    private String phone;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Schema(
            description = "Endereço vinculado ao usuário",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private AddressEntity address;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(
            description = "Data de criação do usuário",
            example = "2026-06-23T10:15:30",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;
}
package br.com.querino.bancodigital.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.querino.bancodigital.config.security.JwtService;
import br.com.querino.bancodigital.dto.auth.AuthResponseDTO;
import br.com.querino.bancodigital.dto.auth.LoginRequestDTO;
import br.com.querino.bancodigital.entity.UserEntity;
import br.com.querino.bancodigital.repository.UserRepository;
import lombok.AllArgsConstructor;

/**
 * Serviço responsável pela autenticação de usuários.
 */
@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Realiza autenticação do usuário e gera um token JWT.
     *
     * @param dto credenciais de acesso
     * @return token JWT e tipo do token
     */
    public AuthResponseDTO login(LoginRequestDTO dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        return new AuthResponseDTO(jwtService.generateToken(user), "Bearer");
    }
}
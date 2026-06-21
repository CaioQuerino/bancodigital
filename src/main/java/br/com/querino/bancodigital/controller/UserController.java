package br.com.querino.bancodigital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.querino.bancodigital.dto.user.CreateUserDTO;
import br.com.querino.bancodigital.dto.user.ListUserDTO;
import br.com.querino.bancodigital.dto.user.UserResponseDTO;
import br.com.querino.bancodigital.service.UserService;
import br.com.querino.bancodigital.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@Valid @RequestBody CreateUserDTO dto) {
        UserResponseDTO savedUser = userService.createUser(dto);

        ApiResponse<UserResponseDTO> response = ApiResponse.<UserResponseDTO>builder()
            .success(true)
            .message("Usuário criado com sucesso")
            .data(savedUser)
            .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ListUserDTO>>> listUsers() {
        List<ListUserDTO> user = userService.listUsers();

        ApiResponse<List<ListUserDTO>> response = ApiResponse.<List<ListUserDTO>>builder()
            .success(true)
            .message("Usuários listados com sucesso")
            .data(user)
            .build();

        return ResponseEntity.ok(response);
    }
}
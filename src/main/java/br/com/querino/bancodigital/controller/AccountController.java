package br.com.querino.bancodigital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.querino.bancodigital.dto.account.AccountrResponseDTO;
import br.com.querino.bancodigital.dto.account.CreateAccountDTO;
import br.com.querino.bancodigital.dto.account.CreateUserAccountDTO;
import br.com.querino.bancodigital.dto.account.ListAccountDTO;
import br.com.querino.bancodigital.service.AccountService;
import br.com.querino.bancodigital.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountrResponseDTO>> createAccount(@Valid @RequestBody CreateAccountDTO dto) {
        if (!hasBankAccess()) {
            return forbiddenResponse("Apenas gerentes e funcionários do banco podem criar contas");
        }

        AccountrResponseDTO savedAccount = accountService.createAccount(dto);

        ApiResponse<AccountrResponseDTO> response = ApiResponse.<AccountrResponseDTO>builder()
            .success(true)
            .message("Conta criada com sucesso")
            .data(savedAccount)
            .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AccountrResponseDTO>> createUserAccount(
            @jakarta.validation.Valid @RequestBody CreateUserAccountDTO dto
    ) {
        if (!hasBankAccess()) {
            return forbiddenResponse("Apenas gerentes e funcionários do banco podem criar contas");
        }

        AccountrResponseDTO savedAccount = accountService.createUserAccount(
                dto.getUser(),
                dto.getAccount()
        );

        ApiResponse<AccountrResponseDTO> response = ApiResponse.<AccountrResponseDTO>builder()
            .success(true)
            .message("Conta criada com sucesso")
            .data(savedAccount)
            .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ListAccountDTO>>> listUsers() {
        List<ListAccountDTO> accounts = accountService.listAccounts();

        ApiResponse<List<ListAccountDTO>> response = ApiResponse.<List<ListAccountDTO>>builder()
            .success(true)
            .message("Contas listadas com sucesso")
            .data(accounts)
            .build();

        return ResponseEntity.ok(response);
    }

    private boolean hasBankAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if ("ROLE_BANK_MANAGER".equals(role) || "ROLE_BANK_EMPLOYEE".equals(role)) {
                return true;
            }
        }
        return false;
    }

    private ResponseEntity<ApiResponse<AccountrResponseDTO>> forbiddenResponse(String message) {
        ApiResponse<AccountrResponseDTO> response = ApiResponse.<AccountrResponseDTO>builder()
            .success(false)
            .message(message)
            .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
package br.com.querino.bancodigital.controller;

import br.com.querino.bancodigital.repository.AccountRepository;
import br.com.querino.bancodigital.repository.UserRepository;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.querino.bancodigital.dto.CreateUserDTO;
import br.com.querino.bancodigital.dto.account.AccountrResponseDTO;
import br.com.querino.bancodigital.dto.account.CreateAccountDTO;
import br.com.querino.bancodigital.dto.account.CreateUserAccountDTO;
import br.com.querino.bancodigital.dto.account.ListAccountDTO;
import br.com.querino.bancodigital.entity.AccountEntity;
import br.com.querino.bancodigital.entity.UserEntity;
import br.com.querino.bancodigital.enums.UserRole;
import br.com.querino.bancodigital.service.AccountService;
import br.com.querino.bancodigital.util.ApiResponse;
import br.com.querino.bancodigital.util.BankUtils;
import br.com.querino.bancodigital.util.Convert;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public AccountrResponseDTO createUserAccount(
            CreateUserDTO userDTO,
            CreateAccountDTO accountDTO
    ) {

        UserEntity user = Convert.to(userDTO, UserEntity::new);

        user.setUserRole(UserRole.AUTONOMOUS);

        user = userRepository.save(user);

        AccountEntity account = new AccountEntity();

        account.setBalance(accountDTO.getBalance());
        account.setCreditLimit(accountDTO.getCreditLimit());
        account.setAccountType(accountDTO.getAccountType());

        account.setAgency(BankUtils.generateAgency());
        account.setAccountNumber(BankUtils.generateAccountNumber());

        account.setUser(user);

        AccountEntity accountSaved = accountRepository.save(account);

        return Convert.to(accountSaved,
                AccountrResponseDTO::new);
    }
    
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AccountrResponseDTO>> createUserAccount(
            @jakarta.validation.Valid @RequestBody CreateUserAccountDTO dto
    ) {

        AccountrResponseDTO savedAccount =
                accountService.createUserAccount(
                        dto.getUser(),
                        dto.getAccount()
                );

        ApiResponse<AccountrResponseDTO> response =
                ApiResponse.<AccountrResponseDTO>builder()
                        .success(true)
                        .message("Conta criada com sucesso")
                        .data(savedAccount)
                        .build();

        return ResponseEntity.status(201).body(response);
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
}
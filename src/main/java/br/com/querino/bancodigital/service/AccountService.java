package br.com.querino.bancodigital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.querino.bancodigital.dto.account.AccountrResponseDTO;
import br.com.querino.bancodigital.dto.account.CreateAccountDTO;
import br.com.querino.bancodigital.dto.account.ListAccountDTO;
import br.com.querino.bancodigital.dto.CreateUserDTO;
import br.com.querino.bancodigital.entity.AccountEntity;
import br.com.querino.bancodigital.entity.UserEntity;
import br.com.querino.bancodigital.repository.AccountRepository;
import br.com.querino.bancodigital.repository.UserRepository;
import br.com.querino.bancodigital.util.BankUtils;
import br.com.querino.bancodigital.util.Convert;
import br.com.querino.bancodigital.enums.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressService addressService;

    public AccountrResponseDTO createAccount(CreateAccountDTO dto) {

        AccountEntity entity = new AccountEntity();

        entity.setBalance(dto.getBalance());
        entity.setCreditLimit(dto.getCreditLimit());
        entity.setAccountType(dto.getAccountType());

        entity.setAgency(BankUtils.generateAgency());
        entity.setAccountNumber(BankUtils.generateAccountNumber());

        AccountEntity savedEntity = accountRepository.save(entity);

        return Convert.to(savedEntity, AccountrResponseDTO::new);
    }

public AccountrResponseDTO createUserAccount(CreateUserDTO userDTO, CreateAccountDTO accountDTO) {

    UserEntity user = Convert.to(userDTO, UserEntity::new);
    user.setUserRole(UserRole.AUTONOMOUS);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    
    if (userDTO.getAddress() != null) {
        user.setAddress(addressService.convertDtoToEntity(userDTO.getAddress()));
        user.getAddress().setUser(user);
    }

    UserEntity savedUser = userRepository.save(user);

    AccountEntity account = new AccountEntity();

    account.setBalance(accountDTO.getBalance());
    account.setCreditLimit(accountDTO.getCreditLimit());
    account.setAccountType(accountDTO.getAccountType());

    account.setAgency(BankUtils.generateAgency());
    account.setAccountNumber(BankUtils.generateAccountNumber());

    account.setUser(savedUser);

    AccountEntity accountSaved = accountRepository.save(account);

    return Convert.to(accountSaved, AccountrResponseDTO::new);
}

    public List<ListAccountDTO> listAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> Convert.to(account, ListAccountDTO::new))
                .collect(Collectors.toList());
    }
}
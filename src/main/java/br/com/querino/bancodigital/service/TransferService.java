package br.com.querino.bancodigital.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.querino.bancodigital.dto.transfer.TransferDTO;
import br.com.querino.bancodigital.dto.transfer.TransferResponseDTO;
import br.com.querino.bancodigital.entity.AccountEntity;
import br.com.querino.bancodigital.entity.Transfer;
import br.com.querino.bancodigital.repository.AccountRepository;
import br.com.querino.bancodigital.repository.TransferRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransferService {
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public TransferResponseDTO createTransfer(TransferDTO dto) {
        AccountEntity sourceAccount = accountRepository.findByAccountNumber(dto.getSourceAccountNumber())
            .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada"));

        AccountEntity destinationAccount = accountRepository.findByAccountNumber(dto.getDestinationAccountNumber())
            .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada"));

        if (sourceAccount.getAccountNumber().equals(destinationAccount.getAccountNumber())) {
            throw new IllegalArgumentException("As contas de origem e destino devem ser diferentes");
        }

        if (dto.getAmount() == null || dto.getAmount() <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero");
        }

        if (sourceAccount.getBalance() == null || sourceAccount.getBalance() < dto.getAmount()) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - dto.getAmount());
        destinationAccount.setBalance(destinationAccount.getBalance() + dto.getAmount());

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transfer transfer = Transfer.builder()
            .amount(dto.getAmount())
            .description(dto.getDescription())
            .originAccount(sourceAccount)
            .destinationAccount(destinationAccount)
            .build();

        Transfer savedTransfer = transferRepository.save(transfer);

        return new TransferResponseDTO(
            savedTransfer.getId() != null ? savedTransfer.getId().toString() : null,
            sourceAccount.getAccountNumber(),
            destinationAccount.getAccountNumber(),
            savedTransfer.getDescription(),
            savedTransfer.getCreatedAt() != null ? savedTransfer.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            savedTransfer.getAmount()
        );
    }

    @Transactional(readOnly = true)
    public List<TransferResponseDTO> listTransfers() {
        return transferRepository.findAll()
            .stream()
            .map(transfer -> new TransferResponseDTO(
                transfer.getId() != null ? transfer.getId().toString() : null,
                transfer.getOriginAccount() != null ? transfer.getOriginAccount().getAccountNumber() : null,
                transfer.getDestinationAccount() != null ? transfer.getDestinationAccount().getAccountNumber() : null,
                transfer.getDescription(),
                transfer.getCreatedAt() != null ? transfer.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null,
                transfer.getAmount()
            ))
            .collect(Collectors.toList());
    }
}
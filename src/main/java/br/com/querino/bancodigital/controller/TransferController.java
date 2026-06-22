package br.com.querino.bancodigital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.querino.bancodigital.dto.transfer.TransferDTO;
import br.com.querino.bancodigital.dto.transfer.TransferResponseDTO;
import br.com.querino.bancodigital.service.TransferService;
import br.com.querino.bancodigital.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transfers")
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<ApiResponse<TransferResponseDTO>> createTransfer(@Valid @RequestBody TransferDTO dto) {
        TransferResponseDTO savedTransfer = transferService.createTransfer(dto);

        ApiResponse<TransferResponseDTO> response = ApiResponse.<TransferResponseDTO>builder()
            .success(true)
            .message("Transferência realizada com sucesso")
            .data(savedTransfer)
            .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransferResponseDTO>>> listTransfers() {
        List<TransferResponseDTO> transfers = transferService.listTransfers();

        ApiResponse<List<TransferResponseDTO>> response = ApiResponse.<List<TransferResponseDTO>>builder()
            .success(true)
            .message("Transferências listadas com sucesso")
            .data(transfers)
            .build();

        return ResponseEntity.ok(response);
    }
}
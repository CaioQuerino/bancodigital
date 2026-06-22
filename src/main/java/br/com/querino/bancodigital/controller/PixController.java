package br.com.querino.bancodigital.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.querino.bancodigital.dto.pix.CreatePixDTO;
import br.com.querino.bancodigital.entity.PixEntity;
import br.com.querino.bancodigital.service.PixService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pix")
@AllArgsConstructor
public class PixController {

    private final PixService pixService;

    @PostMapping
    public ResponseEntity<PixEntity> create(@Valid @RequestBody CreatePixDTO dto) {

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(pixService.createKey(dto));
    }
}
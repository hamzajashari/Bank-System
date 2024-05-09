package com.bank.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.bank.Model.Bank;
import com.bank.Service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BankController {

    private final BankService bankService;

    @PostMapping("/banks")
    public ResponseEntity<?> createBank(@Valid @RequestBody Bank bank) {
        log.debug("Creating a new Bank: {}", bank);
        Bank createdBank = bankService.create(bank);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBank);
    }
}
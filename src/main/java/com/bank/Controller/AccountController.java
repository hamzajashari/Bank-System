package com.bank.Controller;

import javax.validation.Valid;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Model.Account.AccountRequest;
import com.bank.Model.Account.AccountResponse;
import com.bank.Service.BankService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{bankName}/accounts")
public class AccountController {

    private final BankService bankService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@PathVariable String bankName,
            @Valid @RequestBody AccountRequest request) {
        AccountResponse createdAccount = bankService.create(bankName, request);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(@PathVariable String bankName) {
        List<AccountResponse> accounts = bankService.getAll(bankName);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<AccountResponse> checkAccountBalance(@PathVariable String bankName,
            @PathVariable String accountId) {
        AccountResponse balanceResponse = bankService.checkBalance(bankName, accountId);
        return ResponseEntity.ok(balanceResponse);
    }
}
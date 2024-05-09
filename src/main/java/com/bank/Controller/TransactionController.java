package com.bank.Controller;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank.Model.Enum.FeeType;
import com.bank.Model.Transaction.TransactionRequest;
import com.bank.Model.Transaction.TransactionResponse;
import com.bank.Service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private final BankService bankService;

    @PostMapping("/{bankName}/transactions")
    public ResponseEntity<?> createTransaction(@PathVariable String bankName,
            @Valid @RequestBody TransactionRequest transactionRequest,
            @RequestParam(value = "fee-type", required = true) FeeType feeType) {
        try {
            log.debug("Creating a Transaction for Bank '{}': {}", bankName, transactionRequest);
            TransactionResponse createdTransaction = bankService.create(bankName, transactionRequest, feeType);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/{bankName}/transactions")
    public ResponseEntity<?> getAllTransactionsByAccountId(@PathVariable String bankName,
            @RequestParam String accountId) {
        try {
            log.debug("Retrieving all Transactions for Account '{}' in Bank '{}'", accountId, bankName);
            List<TransactionResponse> transactions = bankService.getAllTransactionsByAccountId(accountId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/{bankName}/transactions/total_fee")
    public ResponseEntity<?> getTotalTransactionFee(@PathVariable String bankName) {
        try {
            log.debug("Retrieving the total Transaction fee for Bank '{}'", bankName);
            BigDecimal feeAmount = bankService.getTotalFeeAmount(bankName);
            return ResponseEntity.ok(feeAmount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/{bankName}/transactions/total_transfer")
    public ResponseEntity<?> getTotalTransferFee(@PathVariable String bankName) {
        try {
            log.debug("Retrieving the total amount of transfers for Bank '{}'", bankName);
            BigDecimal amount = bankService.getTotalTransferAmount(bankName);
            return ResponseEntity.ok(amount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
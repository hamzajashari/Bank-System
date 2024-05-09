package com.bank.Model.Transaction;

import javax.validation.constraints.Positive;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record TransactionRequest(@Positive  BigDecimal amount,
        @NotBlank String accountIdFrom,
        @NotBlank String accountIdTo,
        String reason) {

}
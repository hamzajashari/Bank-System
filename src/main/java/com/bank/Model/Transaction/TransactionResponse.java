package com.bank.Model.Transaction;

import java.math.BigDecimal;
import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class TransactionResponse {

  String id;
  BigDecimal amount;
  String from;
  String to;
  String transactionReason;
}
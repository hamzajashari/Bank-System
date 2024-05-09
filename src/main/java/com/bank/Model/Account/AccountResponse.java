package com.bank.Model.Account;

import java.math.BigDecimal;
import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class AccountResponse {
  String id;
  String user;
  BigDecimal balance;
}
package com.bank.Model.Account;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record AccountRequest(@NotEmpty String user, @NotNull BigDecimal balance) {

}
package com.bank.Model.Transaction;

import javax.persistence.*;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import com.bank.Model.Enum.FeeType;
import com.bank.Model.Abstract.AbstractModel;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction extends AbstractModel {

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "account_from", nullable = false)
    private String originatingAccountId;

    @Column(name = "account_to", nullable = false)
    private String resultingAccountId;

    @Column(name = "reason")
    private String transactionReason;

    public void calculateTransactionFee(BigDecimal fee, FeeType feeType) {
        if (feeType.equals(FeeType.PERCENT)) {
            BigDecimal feeAmount = amount.multiply(fee).divide(BigDecimal.valueOf(100), HALF_UP);
            this.amount = amount.subtract(feeAmount);
        } else if (feeType.equals(FeeType.FLAT)) {
            this.amount = amount.subtract(fee);
        }
    }

    public BigDecimal calculatePercentFee(BigDecimal fee) {
        return amount.multiply(fee).divide(BigDecimal.valueOf(100), HALF_UP);
    }

}

package com.atm.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class account {
    private Float balance;
    private Float floatingBalanceM;
    private Long numberAccount;
    private Long NIP;

    public void executeDeposit(Float amount) {
        floatingBalanceM += amount;
    }

    public void executeWithdrawal(Float amount) {
        balance -= amount;
    }
}

package com.atm.system.transaction;

import com.atm.user.account;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
public abstract class transaction {
    protected Long amount;
    protected account toAccount;
    protected boolean isExecuted;

    public abstract void execute();

    protected Long absoluteAmount() {
        return Math.abs(amount);
    }
}

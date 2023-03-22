package com.atm.system.transaction;

import com.atm.user.account;

public abstract class transaction {
    Float amount;

    public abstract void execute(account account);

    protected Float absoluteAmount() {
        return Math.abs(amount);
    }
}

package com.atm.system.transaction;

import com.atm.user.account;

public class deposit extends transaction {

    @Override
    public void execute(account changeAccount) {
        changeAccount.executeTransaction(absoluteAmount());

    }

}

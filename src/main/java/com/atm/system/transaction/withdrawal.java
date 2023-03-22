package com.atm.system.transaction;

import com.atm.user.account;

public class withdrawal extends transaction {

    @Override
    public void execute(account changeAccount) {
        changeAccount.executeTransaction(-absoluteAmount());
    }

}

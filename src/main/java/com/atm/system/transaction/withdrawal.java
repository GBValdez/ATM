package com.atm.system.transaction;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class withdrawal extends transaction {

    @Override
    public void execute() {
        if (!isExecuted)
            toAccount.executeWithdrawal(absoluteAmount().floatValue());
        isExecuted = true;
    }

}

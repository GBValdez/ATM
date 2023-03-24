package com.atm.system.transaction;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class deposit extends transaction {

    @Override
    public void execute() {
        if (!isExecuted)
            this.toAccount.executeTransaction(absoluteAmount());
        isExecuted = true;
    }

}

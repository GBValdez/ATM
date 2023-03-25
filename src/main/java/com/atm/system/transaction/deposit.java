package com.atm.system.transaction;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class deposit extends transaction {

    @Override
    public void execute() {
        if (!isExecuted) {
            final float FINAL_AMOUNT = absoluteAmount() / 100;
            this.toAccount.executeDeposit(FINAL_AMOUNT);
        }
        isExecuted = true;
    }

}

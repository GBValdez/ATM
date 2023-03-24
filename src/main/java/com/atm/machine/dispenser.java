package com.atm.machine;

import com.atm.ATM;

public class dispenser {

    public static boolean isPosibleWithdrawAmount(float amount) {
        return ATM.getAmountAvailable() >= amount;
    }

    public static void deliverAmount(float amount) {
        float finalAmount = ATM.getAmountAvailable() - amount;
        ATM.setAmountAvailable(finalAmount);
    }
}

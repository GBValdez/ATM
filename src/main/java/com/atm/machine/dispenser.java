package com.atm.machine;

import com.atm.ATM;

public class dispenser {

    // Función que verifica si el ATM posee el efectivo suficiente para realizar un
    // deposito
    public static boolean isPosibleWithdrawAmount(float amount) {
        return ATM.getAmountAvailable() >= amount;
    }

    // Función simula la entrega de efectivo al usuario
    public static void deliverAmount(float amount) {
        float finalAmount = ATM.getAmountAvailable() - amount;
        ATM.setAmountAvailable(finalAmount);
    }
}

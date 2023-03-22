package com.atm;

import com.atm.machine.numericKeyboard;
import com.atm.machine.screen;
import com.atm.user.account;

public class ATM {
    account newUser;

    public static void main(String[] args) {
        screen scr = new screen();
        scr.showMessage("Bienvenido\n", "purple");
        scr.showMessage("Ingresa tu numero de cuenta\n", "blue");
        scr.showMessage("", "white");
        numericKeyboard keyboard = new numericKeyboard(scr);
        keyboard.writeLong();

    }
}

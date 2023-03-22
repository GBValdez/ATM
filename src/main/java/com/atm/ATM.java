package com.atm;

import com.atm.machine.numericKeyboard;
import com.atm.machine.screen;
import com.atm.user.account;

public class ATM {
    private static account newUser = new account();
    private static screen scr = new screen();
    private static numericKeyboard keyboard = new numericKeyboard(scr);

    public static void main(String[] args) {
        authentication();

    }

    private static void authentication() {
        do {
            scr.cleanScreen();
            scr.showMessage("Bienvenido\n", "purple");
            scr.showMessage("Ingresa su numero de cuenta\n", "blue");
            final String NUMBER_ACCOUNT = keyboard.writeString();
            scr.showMessage("Ingresa su NIP\n", "blue");
            final String NIP = keyboard.writeString();
            scr.cleanScreen();
            if (!newUser.setCredentials(NUMBER_ACCOUNT, NIP)) {
                scr.showMessage("Error: Numero de cuenta o Nip invalido\n", "red");
                sleep(3000l);
            }
        } while (!newUser.isOpenSession());

    }

    private static void sleep(Long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            scr.showMessage("Error al esperar", "red");
        }
    }
}

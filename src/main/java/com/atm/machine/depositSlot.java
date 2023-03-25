package com.atm.machine;

public class depositSlot {

    public static boolean detectEnvelope() {
        // Ya que no tenemos el hardware necesario para recibir sobres, presione Enter
        // para
        // simular que el usuario
        // ingreso el sobre
        numericKeyboard.getScan().nextLine();
        numericKeyboard.writeString();
        return true;
    }

}

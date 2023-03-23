package com.atm;

import com.atm.machine.numericKeyboard;
import com.atm.machine.screen;
import com.atm.system.internal.menu;
import com.atm.user.account;

public class ATM {
    private static account newUser = new account();

    public static void main(String[] args) {
        authentication();
        menuPrincipal();
    }

    private static void authentication() {
        do {
            screen.cleanScreen();
            screen.showMessage("Bienvenido\n", "purple");
            screen.showMessage("Ingresa su numero de cuenta: ", "blue");
            final String NUMBER_ACCOUNT = numericKeyboard.writeString();
            screen.showMessage("Ingresa su NIP : ", "blue");
            final String NIP = numericKeyboard.writeString();
            screen.cleanScreen();
            if (!newUser.setCredentials(NUMBER_ACCOUNT, NIP)) {
                screen.showMessage("Error: Numero de cuenta o Nip invalido\n", "red");
                sleep(3000l);
            }
        } while (!newUser.isOpenSession());
        screen.showMessage("Bienvenido\n", "green");
    }

    private static void menuPrincipal() {
        String[] options = { "1 - Solicitud de Saldo", "2 - Realizar retiro", "3 - Realizar deposito", "4 - Salir" };
        String[] optionsNumber = { "1", "2", "3", "4" };
        String optionSelected;
        menu menuPrincipal = menu.builder().labels(options).options(optionsNumber).title("Menu De Gestiones")
                .instruction("Ingrese el numero de la gestión que desee realizar: ").build();
        optionSelected = menuPrincipal.executeMenu();
        switch (optionSelected) {
            case "1":
                showBalance();
                break;
            case "2":
                deposit();
            default:
                break;
        }
    }

    private static void showBalance() {
        String balanceText = "Saldo actual: Q" + newUser.getBalance().toString() + "\n";
        screen.showMessage(balanceText, "purple");
        screen.showMessage("Presione enter para salir\n", "blue");
        numericKeyboard.writeString();
        menuPrincipal();
    }

    private static void deposit() {
        String[] labels = { "1 - $20          4 - $100", "2 - $40          5 - $200",
                "3 - $60          6 - Cancelar Transacción" };
        String[] options = { "1", "2", "3", "4", "5", "6" };
        menu depositMenu = menu.builder().title("Menu de retiro").options(options).labels(labels)
                .instruction("Elija un monto de retiro: ").build();
        String optionSelected = depositMenu.executeMenu();
        menuPrincipal();
    }

    private static void sleep(Long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            screen.showMessage("Error al esperar", "red");
        }
    }
}

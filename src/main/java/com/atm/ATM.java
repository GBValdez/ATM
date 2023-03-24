package com.atm;

import com.atm.machine.dispenser;
import com.atm.machine.numericKeyboard;
import com.atm.machine.screen;
import com.atm.system.internal.menu;
import com.atm.system.transaction.deposit;
import com.atm.user.account;
import com.atm.system.transaction.withdrawal;

public class ATM {
    private static account newUser = account.builder().balance(5000f).build();
    private static float amountAvailable = 100;

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
                withdrawal();
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

    private static void withdrawal() {
        String[] labels = { "1 - $20          4 - $100", "2 - $40          5 - $200",
                "3 - $60          6 - Cancelar Transacción" };
        String[] options = { "1", "2", "3", "4", "5", "6" };
        menu depositMenu = menu.builder().title("Menu de retiro").options(options).labels(labels)
                .instruction("Elija un monto de retiro: ").build();
        String optionSelected = depositMenu.executeMenu();

        float[] amountsOptions = { 20, 40, 60, 100, 200, 0 };
        float amount = amountsOptions[Integer.parseInt(optionSelected) - 1];
        if (amount != 0) {
            screen.cleanScreen();
            boolean error = false;
            if (newUser.getBalance() >= amount) {
                if (dispenser.isPosibleWithdrawAmount(amount)) {
                    withdrawal newWithdrawal = withdrawal.builder().amount(amount).toAccount(newUser).build();
                    newWithdrawal.execute();
                    dispenser.deliverAmount(amount);
                    screen.showMessage("Retiro realizado con éxito", "green");
                    screen.showMessage("Por favor tome su efectivo", "green");
                } else {
                    screen.showMessage("El ATM no posee el monto suficiente", "red");
                    error = true;
                }
            } else {
                screen.showMessage("El monto seccionado excede el monto de la cuenta", "red");
                error = true;
            }
            sleep(3000l);
            if (error) {
                withdrawal();
            } else {
                menuPrincipal();
            }
        } else {
            menuPrincipal();
        }

    }

    private static void deposit() {

    }

    // Esta función pausara la ejecución durante un tiempo establecido en
    // milisegundos
    private static void sleep(Long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            screen.showMessage("Error al esperar", "red");
        }
    }

    // Métodos para el saldo disponible dentro del ATM
    public static float getAmountAvailable() {
        return amountAvailable;
    }

    public static void setAmountAvailable(float amount) {
        amountAvailable = amount;
    }
}

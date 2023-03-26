package com.atm;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import com.atm.machine.depositSlot;
import com.atm.machine.dispenser;
import com.atm.machine.numericKeyboard;
import com.atm.machine.screen;
import com.atm.system.internal.dataBase;
import com.atm.system.internal.menu;
import com.atm.system.transaction.deposit;
import com.atm.user.account;

import lombok.Getter;
import lombok.Setter;

import com.atm.system.transaction.withdrawal;

public class ATM {
    private static account newUser;
    @Getter
    @Setter
    private static float amountAvailable = 100;

    // Cargamos datos en el array de la base de datos y ejecutamos la función
    // principal
    public static void main(String[] args) {
        dataBase.connect();
        principal();
    }

    private static void principal() {
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
            if (!Login(NUMBER_ACCOUNT, NIP)) {
                screen.showMessage("Error: Numero de cuenta o Nip invalido\n", "red");
                sleep(3000l);
            }
        } while (newUser == null);
        screen.showMessage("Bienvenido\n", "green");
        sleep(3000l);
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
                break;
            case "3":
                deposit();
                break;
            case "4":
                signOut();
                break;
            default:
                break;

        }
        if (!optionSelected.equals("4")) {
            menuPrincipal();
        }
    }

    // Función para cerrar sesión
    public static void signOut() {
        newUser = null;
        screen.cleanScreen();
        screen.showMessage("Muchas gracias por su visita", "blue");
        sleep(3000l);
        principal();
    }

    // Funcionalidad de mostrar saldo
    private static void showBalance() {
        String balanceText = "Saldo actual: Q" + newUser.getBalance().toString() + "\n";
        screen.showMessage(balanceText, "purple");
        screen.showMessage("Presione enter para salir\n", "blue");
        numericKeyboard.writeString();
    }

    // Funcionalidad para hacer Retiros
    private static void withdrawal() {
        String[] labels = { "1 - $20          4 - $100", "2 - $40          5 - $200",
                "3 - $60          6 - Cancelar Transacción" };
        String[] options = { "1", "2", "3", "4", "5", "6" };
        menu depositMenu = menu.builder().title("Menu de retiro").options(options).labels(labels)
                .instruction("Elija un monto de retiro: ").build();
        String optionSelected = depositMenu.executeMenu();

        int[] amountsOptions = { 20, 40, 60, 100, 200, 0 };
        Integer amount = amountsOptions[Integer.parseInt(optionSelected) - 1];
        if (amount != 0) {
            screen.cleanScreen();
            boolean error = false;
            if (newUser.getBalance() >= amount) {
                if (dispenser.isPosibleWithdrawAmount(amount)) {
                    withdrawal newWithdrawal = withdrawal.builder().amount(amount.longValue()).toAccount(newUser)
                            .build();
                    newWithdrawal.execute();
                    dispenser.deliverAmount(amount);
                    screen.showMessage("Retiro realizado con éxito\n", "green");
                    screen.showMessage("Por favor tome su efectivo", "blue");
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
            }
        }

    }

    // Funcionalidad para hacer depósitos
    private static void deposit() {
        String[] label = { "0 - Ir al menu principal" };
        menu menuDeposit = menu.builder().title("Deposito")
                .instruction("Ingrese la cantidad de centavos que desee depositar: ").labels(label).build();
        Long cents = menuDeposit.getLongExecuteMenu();
        screen.cleanScreen();
        screen.showMessage("Por favor inserte el sobre en la ranura de deposito", "blue");
        if (cents == 0) {
            menuPrincipal();
        } else {
            // Crearemos una variables que dira si el deposito es aun valido
            final AtomicBoolean passTime = new AtomicBoolean(false);
            // Crearemos una timer que se ejecutara en 2 minutos el siguiente código
            // (función run) que cancelara el deposito en caso el usuario no ingrese el
            // sobre
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    screen.cleanScreen();
                    screen.showMessage("Error: Se cancelo la transacción por inactividad\n", "red");
                    screen.showMessage("Por favor inténtelo nuevamente", "blue");
                    passTime.set(true);
                    sleep(1000l * 60l * 2l);

                }
            };
            // Creamos el timer y lo iniciamos
            Timer timer = new Timer();
            timer.schedule(timerTask, 1000 * 5);
            // Esperamos entrada del usuario
            depositSlot.detectEnvelope();
            // En caso de que el usuario ingreso el sobre a tiempo cancelamos el timer y
            // ejecutamos el deposito
            if (!passTime.get()) {
                timer.cancel();
                deposit newDeposit = deposit.builder().amount(cents).toAccount(newUser).build();
                newDeposit.execute();
                screen.showMessage("Deposito realizado con éxito", "green");
                sleep(3000l);
            }
        }
    }

    // Funcionalidad verificara los datos enviados por el login
    private static boolean Login(String number, String nip) {
        try {
            final Long NUMBER = Long.parseLong(number);
            final Long NIP = Long.parseLong(nip);
            boolean valid = (number.toString().length() == 5 && NIP.toString().length() == 5);
            if (valid) {
                account found = dataBase.findAccount(NUMBER, NIP);
                valid = found != null;
                if (valid) {
                    newUser = found;
                }
            }
            return valid;
        } catch (Exception e) {
            return false;
        }
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

}

package com.atm.machine;

import java.util.Scanner;

import lombok.Getter;

public class numericKeyboard {
    @Getter
    private static Scanner scan = new Scanner(System.in);;

    public static String writeString() {
        screen.showMessage("", "white");
        return scan.nextLine();
    }

    public static Long writeNumberLong() {
        screen.showMessage("", "white");
        return scan.hasNextLong() ? scan.nextLong() : null;
    }

    // Función que insistirá que el usuario ingrese un float
    public static float writeNumberMandatory() {
        screen.showMessage("", "white");
        do {
            if (!scan.hasNextFloat()) {
                screen.showMessage("Error: Por favor ingrese unicamente un numero\n", "red");
                screen.showMessage("", "white");
                scan.nextLine();
            }
        } while (!scan.hasNextFloat());
        return scan.nextFloat();
    }
}

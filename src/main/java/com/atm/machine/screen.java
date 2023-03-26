package com.atm.machine;

import java.util.Arrays;

public class screen {
    // Colores disponible para mostrar en consola
    private static String[] colors = { "black", "red", "green", "yellow", "blue", "purple", "cyan", "white" };

    // Devolverá el valor ANSI para colorear las letras en la consola
    private static String findColor(String type) {
        int index = Arrays.asList(colors).indexOf(type);
        return "\u001B[3" + index + "m";
    }

    public static void showMessage(String newMessage, String color) {
        // Si quiere ver el tema de los colores con los colores clásicos coloque la
        // siguiente variable en TRUE
        final boolean classic = false;
        color = findColor(color);
        if (!classic)
            System.out.print(color + newMessage);
        else
            System.out.print("\u001B[47m" + "\u001B[32m" + newMessage);
    }

    public static void showListMessage(String[] listMessage) {
        for (String message : listMessage) {
            showMessage(message + "\n", "yellow");
        }
    }

    public static void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

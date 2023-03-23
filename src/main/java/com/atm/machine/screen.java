package com.atm.machine;

import java.util.Arrays;

public class screen {
    private static String[] colors = { "black", "red", "green", "yellow", "blue", "purple", "cyan", "white" };

    private static String findColor(String type) {
        int index = Arrays.asList(colors).indexOf(type);
        return "\u001B[3" + index + "m";
    }

    public static void showMessage(String newMessage, String color) {
        color = findColor(color);
        System.out.print(color + newMessage);
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

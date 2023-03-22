package com.atm.machine;

import java.util.Arrays;

public class screen {
    private String[] colors = { "black", "red", "green", "yellow", "blue", "purple", "cyan", "white" };

    String findColor(String type) {
        int index = Arrays.asList(colors).indexOf(type);
        return "\u001B[3" + index + "m";
    }

    public void showMessage(String newMessage, String color) {
        color = findColor(color);
        System.out.print(color + newMessage);
    }

    public void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

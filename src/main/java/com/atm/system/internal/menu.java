package com.atm.system.internal;

import java.util.Arrays;

import com.atm.machine.numericKeyboard;
import com.atm.machine.screen;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class menu {
    private String[] labels;
    private String[] options;
    private String title;
    private String instruction;

    private void showMenu() {
        screen.cleanScreen();
        screen.showMessage(title + "\n", "purple");
        screen.showListMessage(labels);
        screen.showMessage(instruction, "blue");
    }

    public String executeMenu() {
        String response;
        do {
            showMenu();
            response = numericKeyboard.writeString();
        } while (!Arrays.asList(options).contains(response));
        screen.cleanScreen();
        return response;
    }

    public Long getLongExecuteMenu() {
        Long response = null;
        do {
            showMenu();
            response = numericKeyboard.writeNumberLong();
        } while (response == null);
        screen.cleanScreen();
        return response;

    }
}

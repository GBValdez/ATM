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
    private boolean error;

    private void showMenu() {
        screen.cleanScreen();
        screen.showMessage(title + "\n", "purple");
        screen.showListMessage(labels);
        if (error)
            screen.showMessage("Error: Ingrese un valor correcto\n", "red");
        screen.showMessage(instruction, "blue");

    }

    public String executeMenu() {
        String response;
        error = false;
        do {
            showMenu();
            response = numericKeyboard.writeString();
            error = !Arrays.asList(options).contains(response);
        } while (error);
        screen.cleanScreen();
        return response;
    }

    public Long getLongExecuteMenu() {
        Long response = null;
        do {
            showMenu();
            response = numericKeyboard.writeNumberLong();
            error = response == null;
            if (error)
                numericKeyboard.getScan().nextLine();
        } while (error);
        screen.cleanScreen();
        return response;

    }
}

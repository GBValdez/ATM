package com.atm.machine;

import java.util.Scanner;

import lombok.Data;

@Data
public class numericKeyboard {
    private Scanner scan = new Scanner(System.in);;
    private screen scr;

    public numericKeyboard(screen thisScreen) {
        this.scr = thisScreen;
    }

    public String writeString() {
        scr.showMessage("", "white");
        return scan.nextLine();
    }

    public Long writeLong() {
        scr.showMessage("", "white");
        boolean error = false;
        Long number = 0l;
        do {
            try {
                number = scan.nextLong();
                error = false;
            } catch (Exception e) {
                error = true;
                this.scr.showMessage("Ingresa solo numeros\n", "red");
                this.scr.showMessage("", "white");
                scan.nextLine();
            }
        } while (error);

        return number;
    }

}

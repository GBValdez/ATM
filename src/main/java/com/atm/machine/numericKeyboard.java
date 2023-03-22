package com.atm.machine;

import java.util.Scanner;

import lombok.Data;

@Data
public class numericKeyboard {
    private Scanner scan;
    private screen scr;

    public numericKeyboard(screen thisScreen) {
        this.scan = new Scanner(System.in);
        this.scr = thisScreen;
    }

    public Long writeLong() {
        boolean error = false;
        Long number = 0l;
        while (error) {
            try {
                number = scan.nextLong();
                error = false;
            } catch (Exception e) {
                error = true;
                this.scr.showMessage("Ingrese un valor valido\n", "red");
                this.scr.showMessage("", "white");
                scan.nextLine();
            }
        }
        return number;
    }

}

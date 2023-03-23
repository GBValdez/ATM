package com.atm.user;

import lombok.Data;

@Data
public class account {
    private Float balance = 0f;
    private Float floatingBalanceM = 0f;
    private Long numberAccount = null;
    private Long NIP = null;

    public boolean setCredentials(String number, String nip) {
        try {
            final Long NUMBER = Long.parseLong(number);
            final Long NIP = Long.parseLong(nip);
            boolean valid = (number.toString().length() == 5 && NIP.toString().length() == 5);
            if (valid) {
                setNumberAccount(NUMBER);
                setNIP(NIP);
            }
            return valid;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOpenSession() {
        return getNumberAccount() != null && getNIP() != null;
    }

    public void executeTransaction(Float amount) {
        balance += amount;
    }

}

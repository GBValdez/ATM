package com.atm.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class account {
    private Float balance;
    private Float floatingBalanceM;
    private Long numberAccount;
    private Long NIP;

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

    public void executeDeposit(Float amount) {
        floatingBalanceM += amount;
    }

    public void executeWithdrawal(Float amount) {
        balance -= amount;
    }
}

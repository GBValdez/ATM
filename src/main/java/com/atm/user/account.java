package com.atm.user;

public class account {
    private Float balance = 0f;
    private Float floatingBalanceM = 0f;
    private Long numberAccount;
    private Long NIP;

    boolean setCredentials(Long number, Long NIP) {
        boolean valid = (number.toString().length() == 5 && NIP.toString().length() == 5);
        if (valid) {
            setNumberAccount(number);
            setNIP(NIP);
        }
        return valid;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getFloatingBalanceM() {
        return floatingBalanceM;
    }

    public void setFloatingBalanceM(Float floatingBalanceM) {
        this.floatingBalanceM = floatingBalanceM;
    }

    public Long getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(Long numberAccount) {
        this.numberAccount = numberAccount;
    }

    public Long getNIP() {
        return NIP;
    }

    public void setNIP(Long nIP) {
        NIP = nIP;
    }

    public void executeTransaction(Float amount) {
        balance += amount;
    }

}

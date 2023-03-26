package com.atm.system.internal;

import java.util.ArrayList;
import java.util.List;

import com.atm.user.account;

public class dataBase {
        private static List<account> data = new ArrayList<>();

        // Imitaremos la descarga de datos de una base de datos , creando un list con 2
        // usuarios
        public static void connect() {
                account testUser = account.builder().NIP(12345l).numberAccount(12345l).balance(300f)
                                .floatingBalanceM(300f)
                                .build();
                account testUser2 = account.builder().NIP(11111l).numberAccount(22222l).balance(50f)
                                .floatingBalanceM(300f)
                                .build();
                data.add(testUser);
                data.add(testUser2);

        }

        // Esta función busca al usuario con las credenciales enviadas
        public static account findAccount(Long numberAccount, Long Nip) {
                return data.stream()
                                .filter(thisAccount -> thisAccount.getNumberAccount().equals(numberAccount)
                                                && thisAccount.getNIP().equals(Nip))
                                .findFirst()
                                .orElse(null);
        }
}

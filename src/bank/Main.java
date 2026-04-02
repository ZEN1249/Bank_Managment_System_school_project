/*
 * Nazwa: Main
 * Parametry: brak
 * Opis: Klasa uruchamiająca program i demonstrująca działanie systemu
 * */
package bank;

import bank.model.*;

public class Main {
    /*
     * Nazwa: main
     * Parametry: args
     * Opis: Punt startowy aplikacji, tworzy obiekty i wykonuje operacje
     * */
    public static void main(String[] args) {
        /*
        Client client = new Client(1,"Jan Kowalski");
        Account account = new SavingsAccount(1, 1000);

        client.addAccount(account);

        account.deposit(500);
        account.withdraw(200);

        System.out.println("Saldo: " + account.getBalance());
         */
        System.out.println("================TEST DZIEDZICZENIA========== ");
        Account acc1 = new SavingsAccount(1, 1000);
        Account acc2 = new CheckingAccount(2, 2000);

        acc1.deposit(100);
        acc2.deposit(200);

        System.out.println("Saving: " + acc1.getBalance());
        System.out.println("Checking: " + acc2.getBalance());
    }
}

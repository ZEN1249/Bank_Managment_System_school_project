/*
 * Nazwa: Main
 * Parametry: brak
 * Opis: Klasa uruchamiająca program i demonstrująca działanie systemu
 * */
package bank;

import bank.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
     * Nazwa: main
     * Parametry: args
     * Opis: Punt startowy aplikacji, tworzy obiekty i wykonuje operacje
     * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        /*
        Client client = new Client(1,"Jan Kowalski");
        Account account = new SavingsAccount(1, 1000);

        client.addAccount(account);

        account.deposit(500);
        account.withdraw(200);

        System.out.println("Saldo: " + account.getBalance());
         */
        /*
        System.out.println("================TEST DZIEDZICZENIA========== ");
        Account acc1 = new SavingsAccount(1, 1000);
        Account acc2 = new CheckingAccount(2, 2000);

        acc1.deposit(100);
        acc2.deposit(200);

        System.out.println("Saving: " + acc1.getBalance());
        System.out.println("Checking: " + acc2.getBalance());
        */
        // UI DO TESTOWANIA!!!!
        while (true) {
            System.out.println("Bank System");
            System.out.println(" 1. Create Account");
            System.out.println(" 2. wpłata");
            System.out.println(" 3. wypłata");
            System.out.println(" 4. showe accoounts");
            System.out.println(" 5. EXIT");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1-> {
                    System.out.println("Podaj typ konta (1=checking, 2 savings): ");
                    int type = scanner.nextInt();
                    System.out.println("Podaj poczatkowe saldo");
                    double balance = scanner.nextDouble();
                    Account acc = (type == 1) ? new CheckingAccount(balance) : new SavingsAccount(balance);
                    accounts.add(acc);
                    System.out.println("Dodano konto o ID: " + acc.getId());
                }
                case 2->{
                    System.out.println("Podaj ID konta ");
                    int id = scanner.nextInt();
                    Account acc = accounts.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
                    if(acc == null) {
                        System.out.println("Nie znalleziono konta");
                        break;
                    }
                    System.out.println("Podaj kwote do wplaty");
                    double balance = scanner.nextDouble();
                    acc.deposit(balance);
                    System.out.println("Nowe saldo" + acc.getBalance());
                }
                case 3->{
                    System.out.println("Podaj ID konta");
                    int id = scanner.nextInt();
                    Account acc = accounts.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
                    if(acc == null) {
                        System.out.println("Nie znalleziono konta");
                        break;
                    }
                    System.out.println("Podaj kwote do wplaty");
                    double balance = scanner.nextDouble();
                    acc.withdraw(balance);
                    System.out.println("Nowe saldo" + acc.getBalance());
                }
                case 4->{
                    if(accounts.isEmpty()) {
                        System.out.println("Nie znalleziono konta");
                        break;
                    }
                    System.out.println("Lista kont:");
                    for(Account acc : accounts) {
                        System.out.println(
                                "ID: " + acc.getId() + " | Typ: " + acc.getClass().getSimpleName() + " | Saldo: " + acc.getBalance()
                        );
                    }
                }
                case 5->{
                    System.exit(0);
                }
                default -> scanner.nextLine();
            }
        }
    }
}
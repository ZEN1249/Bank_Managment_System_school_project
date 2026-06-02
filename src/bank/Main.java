package bank;

import bank.data.FileManager;
import bank.model.*;
import bank.service.AutoSaveThread;
import bank.service.BankService;

import java.util.Scanner;

/*
 * Nazwa: Main
 * Parametry: args
 * Opis: Klasa uruchamiająca program, wyświetlająca menu konsolowe i demonstrująca działanie systemu bankowego.
 */
public class Main {

    /*
     * Nazwa: main
     * Parametry: args
     * Opis: Główny punkt startowy aplikacji, uruchamia menu oraz obsługę operacji bankowych.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService();
        FileManager fileManager = new FileManager();

        final String clientsFile = "clients.csv";
        final String accountsFile = "accounts.csv";
        final String cardsFile = "cards.csv";
        final String transactionsFile = "transactions.csv";

        bankService.loadData(fileManager, clientsFile, accountsFile, cardsFile, transactionsFile);

        AutoSaveThread autoSaveThread = new AutoSaveThread(bankService, fileManager, clientsFile, accountsFile, cardsFile, transactionsFile);
        autoSaveThread.start();

        boolean running = true;

        while (running) {
            System.out.println("\n=== BANK MANAGEMENT SYSTEM ===");
            System.out.println("1. Dodaj klienta");
            System.out.println("2. Utwórz konto");
            System.out.println("3. Wpłata");
            System.out.println("4. Wypłata");
            System.out.println("5. Przelew");
            System.out.println("6. Lista kont");
            System.out.println("7. Lista klientów");
            System.out.println("8. Dodaj kartę do konta");
            System.out.println("9. Historia transakcji");
            System.out.println("10. Nalicz odsetki");
            System.out.println("11. Pobierz opłaty z kont bieżących");
            System.out.println("12. Statystyki banku");
            System.out.println("0. Zapisz i wyjdź");
            System.out.print("Wybierz opcję: ");

            int choice = readInt(scanner);

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Podaj imię i nazwisko klienta: ");
                        String fullName = scanner.nextLine();
                        Client client = bankService.createClient(fullName);
                        System.out.println("Dodano klienta: " + client);
                    }
                    case 2 -> {
                        System.out.print("Podaj ID klienta: ");
                        int clientId = readInt(scanner);
                        System.out.println("Wybierz typ konta: 1 = CHECKING, 2 = SAVINGS");
                        int typeChoice = readInt(scanner);
                        AccountType accountType = (typeChoice == 1) ? AccountType.CHECKING : AccountType.SAVINGS;
                        System.out.print("Podaj saldo początkowe: ");
                        double balance = readDouble(scanner);
                        Account account = bankService.openAccount(clientId, accountType, balance);
                        System.out.println("Utworzono konto: " + account);
                    }
                    case 3 -> {
                        System.out.print("Podaj ID konta: ");
                        int accountId = readInt(scanner);
                        System.out.print("Podaj kwotę wpłaty: ");
                        double amount = readDouble(scanner);
                        bankService.deposit(accountId, amount);
                        System.out.println("Wpłata wykonana pomyślnie.");
                    }
                    case 4 -> {
                        System.out.print("Podaj ID konta: ");
                        int accountId = readInt(scanner);
                        System.out.print("Podaj kwotę wypłaty: ");
                        double amount = readDouble(scanner);
                        bankService.withdraw(accountId, amount);
                        System.out.println("Wypłata wykonana pomyślnie.");
                    }
                    case 5 -> {
                        System.out.print("Podaj ID konta źródłowego: ");
                        int fromId = readInt(scanner);
                        System.out.print("Podaj ID konta docelowego: ");
                        int toId = readInt(scanner);
                        System.out.print("Podaj kwotę przelewu: ");
                        double amount = readDouble(scanner);
                        bankService.transfer(fromId, toId, amount);
                        System.out.println("Przelew wykonany pomyślnie.");
                    }
                    case 6 -> {
                        System.out.println("=== LISTA KONT ===");
                        for (Account account : bankService.getAccounts()) {
                            System.out.println(account);
                        }
                    }
                    case 7 -> {
                        System.out.println("=== LISTA KLIENTÓW ===");
                        for (Client client : bankService.getClients()) {
                            System.out.println(client);
                        }
                    }
                    case 8 -> {
                        System.out.print("Podaj ID konta: ");
                        int accountId = readInt(scanner);
                        System.out.print("Podaj typ karty (np. DEBIT / CREDIT): ");
                        String cardType = scanner.nextLine().trim().toUpperCase();
                        Card card = bankService.assignCardToAccount(accountId, cardType);
                        System.out.println("Dodano kartę: " + card);
                    }
                    case 9 -> {
                        System.out.println("=== HISTORIA TRANSAKCJI ===");
                        for (Transaction transaction : bankService.getTransactions()) {
                            System.out.println(transaction);
                        }
                    }
                    case 10 -> {
                        bankService.addInterestForSavingsAccounts();
                        System.out.println("Naliczono odsetki dla kont oszczędnościowych.");
                    }
                    case 11 -> {
                        bankService.chargeCheckingFees();
                        System.out.println("Pobrano opłaty z kont bieżących.");
                    }
                    case 12 -> System.out.println(bankService.getStatistics());
                    case 0 -> {
                        bankService.saveData(fileManager, clientsFile, accountsFile, cardsFile, transactionsFile);
                        autoSaveThread.stopSaving();
                        running = false;
                        System.out.println("Dane zapisano. Program zakończony.");
                    }
                    default -> System.out.println("Nieprawidłowy wybór.");
                }
            } catch (Exception e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        }

        scanner.close();
    }

    /*
     * Nazwa: readInt
     * Parametry: scanner
     * Opis: Bezpiecznie odczytuje liczbę całkowitą z konsoli.
     */
    private static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Podaj poprawną liczbę całkowitą: ");
            }
        }
    }

    /*
     * Nazwa: readDouble
     * Parametry: scanner
     * Opis: Bezpiecznie odczytuje liczbę zmiennoprzecinkową z konsoli.
     */
    private static double readDouble(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Podaj poprawną kwotę: ");
            }
        }
    }
}
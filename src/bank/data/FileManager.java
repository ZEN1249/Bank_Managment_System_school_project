package bank.data;

import bank.model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Nazwa: FileManager
 * Parametry: brak
 * Opis: Klasa odpowiedzialna za zapis i odczyt danych bankowych do i z plików CSV.
 */
public class FileManager {

    /*
     * Nazwa: saveClientsToCsv
     * Parametry: clients, fileName
     * Opis: Zapisuje listę klientów do pliku CSV.
     */
    public void saveClientsToCsv(List<Client> clients, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("id;fullName");
            writer.newLine();
            for (Client client : clients) {
                writer.write(client.getId() + ";" + client.getFullName());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu klientów: " + e.getMessage());
        }
    }

    /*
     * Nazwa: saveAccountsToCsv
     * Parametry: accounts, fileName
     * Opis: Zapisuje listę kont do pliku CSV.
     */
    public void saveAccountsToCsv(List<Account> accounts, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("id;ownerId;type;balance");
            writer.newLine();
            for (Account account : accounts) {
                writer.write(account.getId() + ";" + account.getOwnerId() + ";" + account.getAccountType() + ";" + account.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu kont: " + e.getMessage());
        }
    }

    /*
     * Nazwa: saveCardsToCsv
     * Parametry: cards, fileName
     * Opis: Zapisuje listę kart do pliku CSV.
     */
    public void saveCardsToCsv(List<Card> cards, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("id;accountId;cardNumber;cardType");
            writer.newLine();
            for (Card card : cards) {
                writer.write(card.getId() + ";" + card.getAccountId() + ";" + card.getCardNumber() + ";" + card.getCardType());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu kart: " + e.getMessage());
        }
    }

    /*
     * Nazwa: saveTransactionsToCsv
     * Parametry: transactions, fileName
     * Opis: Zapisuje listę transakcji do pliku CSV.
     */
    public void saveTransactionsToCsv(List<Transaction> transactions, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("id;type;sourceAccountId;targetAccountId;amount;description;dateTime");
            writer.newLine();
            for (Transaction transaction : transactions) {
                String target = transaction.getTargetAccountId() == null ? "" : transaction.getTargetAccountId().toString();
                writer.write(transaction.getId() + ";" +
                        transaction.getType() + ";" +
                        transaction.getSourceAccountId() + ";" +
                        target + ";" +
                        transaction.getAmount() + ";" +
                        transaction.getDescription() + ";" +
                        transaction.getDateTime());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu transakcji: " + e.getMessage());
        }
    }

    /*
     * Nazwa: loadClientsFromCsv
     * Parametry: fileName
     * Opis: Wczytuje listę klientów z pliku CSV.
     */
    public List<Client> loadClientsFromCsv(String fileName) {
        List<Client> clients = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return clients;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length < 2) continue;
                clients.add(new Client(Integer.parseInt(parts[0]), parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu klientów: " + e.getMessage());
        }
        return clients;
    }

    /*
     * Nazwa: loadAccountsFromCsv
     * Parametry: fileName
     * Opis: Wczytuje listę kont z pliku CSV.
     */
    public List<Account> loadAccountsFromCsv(String fileName) {
        List<Account> accounts = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return accounts;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length < 4) continue;

                int id = Integer.parseInt(parts[0]);
                int ownerId = Integer.parseInt(parts[1]);
                AccountType type = AccountType.valueOf(parts[2]);
                double balance = Double.parseDouble(parts[3]);

                if (type == AccountType.SAVINGS) {
                    accounts.add(new SavingsAccount(id, ownerId, balance));
                } else {
                    accounts.add(new CheckingAccount(id, ownerId, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu kont: " + e.getMessage());
        }
        return accounts;
    }

    /*
     * Nazwa: loadCardsFromCsv
     * Parametry: fileName
     * Opis: Wczytuje listę kart z pliku CSV.
     */
    public List<Card> loadCardsFromCsv(String fileName) {
        List<Card> cards = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return cards;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length < 4) continue;
                cards.add(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2], parts[3]));
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu kart: " + e.getMessage());
        }
        return cards;
    }

    /*
     * Nazwa: loadTransactionsFromCsv
     * Parametry: fileName
     * Opis: Wczytuje listę transakcji z pliku CSV.
     */
    public List<Transaction> loadTransactionsFromCsv(String fileName) {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return transactions;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length < 7) continue;
                int id = Integer.parseInt(parts[0]);
                TransactionType type = TransactionType.valueOf(parts[1]);
                int sourceAccountId = Integer.parseInt(parts[2]);
                Integer targetAccountId = parts[3].isEmpty() ? null : Integer.parseInt(parts[3]);
                double amount = Double.parseDouble(parts[4]);
                String description = parts[5];
                LocalDateTime dateTime = LocalDateTime.parse(parts[6]);
                transactions.add(new Transaction(id, type, sourceAccountId, targetAccountId, amount, description, dateTime));
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu transakcji: " + e.getMessage());
        }
        return transactions;
    }
}
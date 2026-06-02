package bank.service;

import bank.data.FileManager;
import bank.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Nazwa: BankService
 * Parametry: brak
 * Opis: Klasa odpowiedzialna za główną logikę systemu bankowego, zarządzanie klientami,
 *       kontami, kartami, transakcjami oraz podstawowymi operacjami finansowymi.
 */
public class BankService {

    private final List<Client> clients = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    /*
     * Nazwa: createClient
     * Parametry: fullName
     * Opis: Tworzy nowego klienta i dodaje go do systemu.
     */
    public Client createClient(String fullName) {
        Client client = new Client(fullName);
        clients.add(client);
        return client;
    }

    /*
     * Nazwa: openAccount
     * Parametry: clientId, accountType, initialBalance
     * Opis: Tworzy nowe konto wybranego typu i przypisuje je do wskazanego klienta.
     */
    public Account openAccount(int clientId, AccountType accountType, double initialBalance) {
        Client client = findClientById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Nie znaleziono klienta o ID: " + clientId);
        }

        Account account;
        if (accountType == AccountType.SAVINGS) {
            account = new SavingsAccount(clientId, initialBalance);
        } else if (accountType == AccountType.CHECKING) {
            account = new CheckingAccount(clientId, initialBalance);
        } else {
            throw new IllegalArgumentException("Nieznany typ konta.");
        }

        client.addAccount(account);
        accounts.add(account);

        if (initialBalance > 0) {
            Transaction transaction = new Transaction(
                    TransactionType.DEPOSIT,
                    account.getId(),
                    null,
                    initialBalance,
                    "Wpłata początkowa"
            );
            account.addTransaction(transaction);
            transactions.add(transaction);
        }

        return account;
    }

    /*
     * Nazwa: deposit
     * Parametry: accountId, amount
     * Opis: Wykonuje wpłatę na wskazane konto i zapisuje transakcję.
     */
    public void deposit(int accountId, double amount) {
        Account account = findAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Nie znaleziono konta o ID: " + accountId);
        }

        account.deposit(amount);

        Transaction transaction = new Transaction(
                TransactionType.DEPOSIT,
                account.getId(),
                null,
                amount,
                "Wpłata na konto"
        );

        account.addTransaction(transaction);
        transactions.add(transaction);
    }

    /*
     * Nazwa: withdraw
     * Parametry: accountId, amount
     * Opis: Wykonuje wypłatę z konta i zapisuje transakcję.
     */
    public void withdraw(int accountId, double amount) {
        Account account = findAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Nie znaleziono konta o ID: " + accountId);
        }

        account.withdraw(amount);

        Transaction transaction = new Transaction(
                TransactionType.WITHDRAW,
                account.getId(),
                null,
                amount,
                "Wypłata z konta"
        );

        account.addTransaction(transaction);
        transactions.add(transaction);
    }

    /*
     * Nazwa: transfer
     * Parametry: fromAccountId, toAccountId, amount
     * Opis: Wykonuje przelew pomiędzy dwoma kontami i zapisuje transakcję.
     */
    public void transfer(int fromAccountId, int toAccountId, double amount) {
        if (fromAccountId == toAccountId) {
            throw new IllegalArgumentException("Konto źródłowe i docelowe nie mogą być takie same.");
        }

        Account from = findAccountById(fromAccountId);
        Account to = findAccountById(toAccountId);

        if (from == null || to == null) {
            throw new IllegalArgumentException("Nie znaleziono jednego z kont.");
        }

        from.withdraw(amount);
        to.deposit(amount);

        Transaction transaction = new Transaction(
                TransactionType.TRANSFER,
                from.getId(),
                to.getId(),
                amount,
                "Przelew międzykontowy"
        );

        from.addTransaction(transaction);
        to.addTransaction(transaction);
        transactions.add(transaction);
    }

    /*
     * Nazwa: assignCardToAccount
     * Parametry: accountId, cardType
     * Opis: Tworzy kartę płatniczą i przypisuje ją do wybranego konta.
     */
    public Card assignCardToAccount(int accountId, String cardType) {
        Account account = findAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Nie znaleziono konta o ID: " + accountId);
        }

        if (account.getCard() != null) {
            cards.removeIf(card -> card.getId() == account.getCard().getId());
        }

        Card card = new Card(account.getId(), cardType);
        account.assignCard(card);
        cards.add(card);
        return card;
    }

    /*
     * Nazwa: addInterestForSavingsAccounts
     * Parametry: brak
     * Opis: Nalicza odsetki dla wszystkich kont oszczędnościowych i zapisuje operacje w historii.
     */
    public void addInterestForSavingsAccounts() {
        for (Account account : accounts) {
            if (account instanceof SavingsAccount) {
                double before = account.getBalance();
                ((SavingsAccount) account).addInterest();
                double interest = account.getBalance() - before;

                Transaction transaction = new Transaction(
                        TransactionType.INTEREST,
                        account.getId(),
                        null,
                        interest,
                        "Naliczono odsetki"
                );

                account.addTransaction(transaction);
                transactions.add(transaction);
            }
        }
    }

    /*
     * Nazwa: chargeCheckingFees
     * Parametry: brak
     * Opis: Pobiera opłaty miesięczne z kont bieżących i zapisuje operacje w historii.
     */
    public void chargeCheckingFees() {
        for (Account account : accounts) {
            if (account instanceof CheckingAccount) {
                double before = account.getBalance();
                ((CheckingAccount) account).monthlyFee();
                double fee = before - account.getBalance();

                if (fee > 0) {
                    Transaction transaction = new Transaction(
                            TransactionType.FEE,
                            account.getId(),
                            null,
                            fee,
                            "Opłata miesięczna"
                    );

                    account.addTransaction(transaction);
                    transactions.add(transaction);
                }
            }
        }
    }

    /*
     * Nazwa: findClientById
     * Parametry: id
     * Opis: Wyszukuje klienta na podstawie identyfikatora.
     */
    public Client findClientById(int id) {
        return clients.stream().filter(client -> client.getId() == id).findFirst().orElse(null);
    }

    /*
     * Nazwa: findAccountById
     * Parametry: id
     * Opis: Wyszukuje konto na podstawie identyfikatora.
     */
    public Account findAccountById(int id) {
        return accounts.stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }

    /*
     * Nazwa: findCardByAccountId
     * Parametry: accountId
     * Opis: Wyszukuje kartę przypisaną do konkretnego konta.
     */
    public Card findCardByAccountId(int accountId) {
        return cards.stream().filter(card -> card.getAccountId() == accountId).findFirst().orElse(null);
    }

    /*
     * Nazwa: getClients
     * Parametry: brak
     * Opis: Zwraca listę wszystkich klientów.
     */
    public List<Client> getClients() {
        return clients;
    }

    /*
     * Nazwa: getAccounts
     * Parametry: brak
     * Opis: Zwraca listę wszystkich kont.
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /*
     * Nazwa: getCards
     * Parametry: brak
     * Opis: Zwraca listę wszystkich kart.
     */
    public List<Card> getCards() {
        return cards;
    }

    /*
     * Nazwa: getTransactions
     * Parametry: brak
     * Opis: Zwraca listę wszystkich transakcji.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /*
     * Nazwa: getAccountsOfClient
     * Parametry: clientId
     * Opis: Zwraca wszystkie konta przypisane do konkretnego klienta.
     */
    public List<Account> getAccountsOfClient(int clientId) {
        Client client = findClientById(clientId);
        if (client == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(client.getAccounts());
    }

    /*
     * Nazwa: getStatistics
     * Parametry: brak
     * Opis: Zwraca podstawowe statystyki systemu bankowego w formie tekstowej.
     */
    public String getStatistics() {
        double totalBalance = accounts.stream().mapToDouble(Account::getBalance).sum();
        double averageBalance = accounts.isEmpty() ? 0.0 : totalBalance / accounts.size();
        double maxBalance = accounts.stream().mapToDouble(Account::getBalance).max().orElse(0.0);

        return "Statystyki banku:\n" +
                "Liczba klientów: " + clients.size() + "\n" +
                "Liczba kont: " + accounts.size() + "\n" +
                "Liczba kart: " + cards.size() + "\n" +
                "Liczba transakcji: " + transactions.size() + "\n" +
                "Suma sald: " + totalBalance + "\n" +
                "Średnie saldo: " + averageBalance + "\n" +
                "Najwyższe saldo: " + maxBalance;
    }

    /*
     * Nazwa: loadData
     * Parametry: fileManager, clientsFile, accountsFile, cardsFile, transactionsFile
     * Opis: Wczytuje dane z plików i odtwarza połączenia pomiędzy klientami, kontami, kartami oraz transakcjami.
     */
    public void loadData(FileManager fileManager,
                         String clientsFile,
                         String accountsFile,
                         String cardsFile,
                         String transactionsFile) {
        clients.clear();
        accounts.clear();
        cards.clear();
        transactions.clear();

        clients.addAll(fileManager.loadClientsFromCsv(clientsFile));
        accounts.addAll(fileManager.loadAccountsFromCsv(accountsFile));
        cards.addAll(fileManager.loadCardsFromCsv(cardsFile));
        transactions.addAll(fileManager.loadTransactionsFromCsv(transactionsFile));

        for (Account account : accounts) {
            Client client = findClientById(account.getOwnerId());
            if (client != null) {
                client.addAccount(account);
            }
        }

        for (Card card : cards) {
            Account account = findAccountById(card.getAccountId());
            if (account != null) {
                account.assignCard(card);
            }
        }

        for (Transaction transaction : transactions) {
            Account source = findAccountById(transaction.getSourceAccountId());
            if (source != null) {
                source.addTransaction(transaction);
            }

            Integer targetId = transaction.getTargetAccountId();
            if (targetId != null) {
                Account target = findAccountById(targetId);
                if (target != null) {
                    target.addTransaction(transaction);
                }
            }
        }
    }

    /*
     * Nazwa: saveData
     * Parametry: fileManager, clientsFile, accountsFile, cardsFile, transactionsFile
     * Opis: Zapisuje wszystkie dane systemu do plików CSV.
     */
    public void saveData(FileManager fileManager,
                         String clientsFile,
                         String accountsFile,
                         String cardsFile,
                         String transactionsFile) {
        fileManager.saveClientsToCsv(clients, clientsFile);
        fileManager.saveAccountsToCsv(accounts, accountsFile);
        fileManager.saveCardsToCsv(cards, cardsFile);
        fileManager.saveTransactionsToCsv(transactions, transactionsFile);
    }

    /*
     * Nazwa: getAccountsAsText
     * Parametry: brak
     * Opis: Zwraca listę kont w formie tekstowej.
     */
    public List<String> getAccountsAsText() {
        return accounts.stream()
                .map(Account::toString)
                .collect(Collectors.toList());
    }
}
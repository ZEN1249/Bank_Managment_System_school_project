package bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Nazwa: Account
 * Parametry: ownerId, balance
 * Opis: Abstrakcyjna klasa bazowa reprezentująca ogólne konto bankowe.
 *       Przechowuje identyfikator konta, właściciela, saldo, kartę płatniczą oraz historię transakcji.
 */
public abstract class Account {

    private static int nextId = 1;
    protected int id;
    protected int ownerId;
    protected double balance;
    protected Card card;
    protected final List<Transaction> transactions;

    /*
     * Nazwa: Account (konstruktor)
     * Parametry: ownerId, balance
     * Opis: Tworzy konto i nadaje mu automatyczne ID.
     */
    public Account(int ownerId, double balance) {
        this(nextId, ownerId, balance);
    }

    /*
     * Nazwa: Account (konstruktor)
     * Parametry: id, ownerId, balance
     * Opis: Tworzy konto z jawnie podanym identyfikatorem, używane głównie przy wczytywaniu danych.
     */
    protected Account(int id, int ownerId, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Saldo początkowe nie może być ujemne.");
        }
        this.id = id;
        this.ownerId = ownerId;
        this.balance = balance;
        this.transactions = new ArrayList<>();

        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    /*
     * Nazwa: deposit
     * Parametry: amount
     * Opis: Dodaje określoną kwotę do salda konta.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota wpłaty musi być większa od zera.");
        }
        balance += amount;
    }

    /*
     * Nazwa: withdraw
     * Parametry: amount
     * Opis: Zmniejsza saldo konta o podaną kwotę, jeśli środki są wystarczające.
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota wypłaty musi być większa od zera.");
        }
        if (balance < amount) {
            throw new IllegalStateException("Brak wystarczających środków na koncie.");
        }
        balance -= amount;
    }

    /*
     * Nazwa: assignCard
     * Parametry: card
     * Opis: Przypisuje kartę płatniczą do konta.
     */
    public void assignCard(Card card) {
        this.card = card;
    }

    /*
     * Nazwa: addTransaction
     * Parametry: transaction
     * Opis: Dodaje transakcję do lokalnej historii konta.
     */
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    /*
     * Nazwa: getId
     * Parametry: brak
     * Opis: Zwraca identyfikator konta.
     */
    public int getId() {
        return id;
    }

    /*
     * Nazwa: getOwnerId
     * Parametry: brak
     * Opis: Zwraca identyfikator właściciela konta.
     */
    public int getOwnerId() {
        return ownerId;
    }

    /*
     * Nazwa: getBalance
     * Parametry: brak
     * Opis: Zwraca aktualne saldo konta.
     */
    public double getBalance() {
        return balance;
    }

    /*
     * Nazwa: getCard
     * Parametry: brak
     * Opis: Zwraca kartę przypisaną do konta.
     */
    public Card getCard() {
        return card;
    }

    /*
     * Nazwa: getTransactions
     * Parametry: brak
     * Opis: Zwraca niezmienialną listę transakcji przypisanych do konta.
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    /*
     * Nazwa: getAccountType
     * Parametry: brak
     * Opis: Zwraca typ konkretnego konta.
     */
    public abstract AccountType getAccountType();

    /*
     * Nazwa: toString
     * Parametry: brak
     * Opis: Zwraca tekstową reprezentację konta.
     */
    @Override
    public String toString() {
        return "Account{id=" + id +
                ", ownerId=" + ownerId +
                ", type=" + getAccountType() +
                ", balance=" + balance +
                ", card=" + (card != null ? card.getCardNumber() : "brak") +
                '}';
    }
}
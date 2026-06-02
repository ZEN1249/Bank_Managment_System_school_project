package bank.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Nazwa: Transaction
 * Parametry: id, type, sourceAccountId, targetAccountId, amount, description
 * Opis: Klasa reprezentująca pojedynczą transakcję bankową, taką jak wpłata, wypłata, przelew, opłata lub naliczenie odsetek.
 */
public class Transaction {

    private static int nextId = 1;
    private final int id;
    private final TransactionType type;
    private final int sourceAccountId;
    private final Integer targetAccountId;
    private final double amount;
    private final String description;
    private final LocalDateTime dateTime;

    /*
     * Nazwa: Transaction (konstruktor)
     * Parametry: type, sourceAccountId, targetAccountId, amount, description
     * Opis: Tworzy nową transakcję i zapisuje bieżący czas jej utworzenia.
     */
    public Transaction(TransactionType type, int sourceAccountId, Integer targetAccountId, double amount, String description) {
        this(nextId, type, sourceAccountId, targetAccountId, amount, description, LocalDateTime.now());
    }

    /*
     * Nazwa: Transaction (konstruktor)
     * Parametry: id, type, sourceAccountId, targetAccountId, amount, description, dateTime
     * Opis: Tworzy transakcję z jawnie podanym identyfikatorem oraz datą, używana głównie przy wczytywaniu danych.
     */
    public Transaction(int id, TransactionType type, int sourceAccountId, Integer targetAccountId, double amount, String description, LocalDateTime dateTime) {
        this.id = id;
        this.type = type;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;

        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    /*
     * Nazwa: getId
     * Parametry: brak
     * Opis: Zwraca identyfikator transakcji.
     */
    public int getId() {
        return id;
    }

    /*
     * Nazwa: getType
     * Parametry: brak
     * Opis: Zwraca typ transakcji.
     */
    public TransactionType getType() {
        return type;
    }

    /*
     * Nazwa: getSourceAccountId
     * Parametry: brak
     * Opis: Zwraca identyfikator konta źródłowego.
     */
    public int getSourceAccountId() {
        return sourceAccountId;
    }

    /*
     * Nazwa: getTargetAccountId
     * Parametry: brak
     * Opis: Zwraca identyfikator konta docelowego lub null, jeśli transakcja go nie ma.
     */
    public Integer getTargetAccountId() {
        return targetAccountId;
    }

    /*
     * Nazwa: getAmount
     * Parametry: brak
     * Opis: Zwraca kwotę transakcji.
     */
    public double getAmount() {
        return amount;
    }

    /*
     * Nazwa: getDescription
     * Parametry: brak
     * Opis: Zwraca opis transakcji.
     */
    public String getDescription() {
        return description;
    }

    /*
     * Nazwa: getDateTime
     * Parametry: brak
     * Opis: Zwraca datę i godzinę utworzenia transakcji.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /*
     * Nazwa: toString
     * Parametry: brak
     * Opis: Zwraca pełny opis transakcji w postaci tekstowej.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Transaction{id=" + id +
                ", type=" + type +
                ", sourceAccountId=" + sourceAccountId +
                ", targetAccountId=" + targetAccountId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime.format(formatter) +
                '}';
    }
}
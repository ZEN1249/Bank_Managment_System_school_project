package bank.model;

/*
 * Nazwa: Card
 * Parametry: accountId, cardType
 * Opis: Klasa reprezentująca kartę płatniczą przypisaną do konkretnego konta bankowego.
 */
public class Card {
    private static int nextId = 1;
    private final int id;
    private final int accountId;
    private final String cardNumber;
    private final String cardType;

    /*
     * Nazwa: Card (konstruktor)
     * Parametry: accountId, cardType
     * Opis: Tworzy nową kartę i generuje jej numer automatycznie.
     */
    public Card(int accountId, String cardType) {
        this(nextId, accountId, generateCardNumber(nextId), cardType);
    }

    /*
     * Nazwa: Card (konstruktor)
     * Parametry: id, accountId, cardNumber, cardType
     * Opis: Tworzy kartę z jawnie podanym identyfikatorem, używana głównie przy wczytywaniu danych.
     */
    public Card(int id, int accountId, String cardNumber, String cardType) {
        this.id = id;
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.cardType = cardType;

        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    /*
     * Nazwa: generateCardNumber
     * Parametry: seed
     * Opis: Generuje prosty, unikalny numer karty na podstawie wartości pomocniczej.
     */
    private static String generateCardNumber(int seed) {
        return String.format("4000-%04d-%04d-%04d",
                seed % 10000,
                (seed * 7) % 10000,
                (seed * 13) % 10000);
    }

    /*
     * Nazwa: getId
     * Parametry: brak
     * Opis: Zwraca identyfikator karty.
     */
    public int getId() {
        return id;
    }

    /*
     * Nazwa: getAccountId
     * Parametry: brak
     * Opis: Zwraca identyfikator konta powiązanego z kartą.
     */
    public int getAccountId() {
        return accountId;
    }

    /*
     * Nazwa: getCardNumber
     * Parametry: brak
     * Opis: Zwraca numer karty płatniczej.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /*
     * Nazwa: getCardType
     * Parametry: brak
     * Opis: Zwraca typ karty płatniczej.
     */
    public String getCardType() {
        return cardType;
    }

    /*
     * Nazwa: toString
     * Parametry: brak
     * Opis: Zwraca tekstową reprezentację karty.
     */
    @Override
    public String toString() {
        return "Card{id=" + id +
                ", accountId=" + accountId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
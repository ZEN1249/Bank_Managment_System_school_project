package bank.model;

/*
 * Nazwa: SavingsAccount
 * Parametry: ownerId, balance
 * Opis: Klasa reprezentująca konto oszczędnościowe dziedziczące po klasie Account.
 *       Konto to może naliczać odsetki od salda.
 */
public class SavingsAccount extends Account {

    /*
     * Nazwa: SavingsAccount (konstruktor)
     * Parametry: ownerId, balance
     * Opis: Tworzy konto oszczędnościowe z automatycznie nadanym ID.
     */
    public SavingsAccount(int ownerId, double balance) {
        super(ownerId, balance);
    }

    /*
     * Nazwa: SavingsAccount (konstruktor)
     * Parametry: id, ownerId, balance
     * Opis: Tworzy konto oszczędnościowe z jawnie podanym ID, używane przy wczytywaniu danych.
     */
    public SavingsAccount(int id, int ownerId, double balance) {
        super(id, ownerId, balance);
    }

    /*
     * Nazwa: addInterest
     * Parametry: brak
     * Opis: Nalicza 5% odsetek do aktualnego salda konta.
     */
    public void addInterest() {
        balance *= 1.05;
    }

    /*
     * Nazwa: getAccountType
     * Parametry: brak
     * Opis: Zwraca typ konta oszczędnościowego.
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }
}
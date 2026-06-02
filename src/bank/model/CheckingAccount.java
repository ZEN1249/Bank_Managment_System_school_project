package bank.model;

/*
 * Nazwa: CheckingAccount
 * Parametry: ownerId, balance
 * Opis: Klasa reprezentująca konto bieżące dziedziczące po klasie Account.
 *       Konto to może mieć naliczaną miesięczną opłatę za prowadzenie.
 */
public class CheckingAccount extends Account {

    /*
     * Nazwa: CheckingAccount (konstruktor)
     * Parametry: ownerId, balance
     * Opis: Tworzy konto bieżące z automatycznie nadanym ID.
     */
    public CheckingAccount(int ownerId, double balance) {
        super(ownerId, balance);
    }

    /*
     * Nazwa: CheckingAccount (konstruktor)
     * Parametry: id, ownerId, balance
     * Opis: Tworzy konto bieżące z jawnie podanym ID, używane przy wczytywaniu danych.
     */
    public CheckingAccount(int id, int ownerId, double balance) {
        super(id, ownerId, balance);
    }

    /*
     * Nazwa: monthlyFee
     * Parametry: brak
     * Opis: Pobiera miesięczną opłatę za prowadzenie konta, jeśli saldo jest wystarczające.
     */
    public void monthlyFee() {
        if (balance >= 10) {
            balance -= 10;
        }
    }

    /*
     * Nazwa: getAccountType
     * Parametry: brak
     * Opis: Zwraca typ konta bieżącego.
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
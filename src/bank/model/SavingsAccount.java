/*
* Nazwa: SavingsAccount
* Parametry: id, balance
* Opis: Reprezentuje konto oszczędnościowe dziedziczące po klasie Account
* */

package bank.model;

public class SavingsAccount extends Account {

    /*
     * Nazwa: SavingsAccount (konstruktor)
     * Parametry: id, balance
     * Opis: Tworzy konto osczzędnościowe z określonym saldem początkowym
     * */
    public SavingsAccount(double balance) {
        super(balance);
    }
    /*
     * Nazwa: addIntrest
     * Parametry: brak
     * Opis: dodaje 5% odsetek do aktulanego salda konta.
     * */
    public void addInterest() {
        balance *= 1.05;
    }
}

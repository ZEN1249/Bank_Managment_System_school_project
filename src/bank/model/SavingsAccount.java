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
    public SavingsAccount(int id, double balance) {
        super(id, balance);
    }
}

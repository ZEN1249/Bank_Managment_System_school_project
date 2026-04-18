/*
* Nazwa: CheckingAccount
* Parametry: balance
* Opis: konto bieżące
* */
package bank.model;

public class CheckingAccount extends Account{
    /*
     * Nazwa: CheckingAccount (konstruktor)
     * Parametry: balance
     * Opis: Tworzy konto osczzędnościowe z określonym saldem początkowym
     * */
    public CheckingAccount(double balance) {
        super(balance);
    }
    /*
     * Nazwa: monthlyFee
     * Parametry: brak
     * Opis: Pobiera miesięczną opłatę za prowadzenie konta
     * */
    public void monthlyFee(){
        balance -= 10;
    }
}

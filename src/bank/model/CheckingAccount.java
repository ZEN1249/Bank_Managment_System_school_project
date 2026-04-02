/*
* Nazwa: CheckingAccount
* Parametry: id, balance
* Opis: konto bieżące
* */
package bank.model;

public class CheckingAccount extends Account{
    public CheckingAccount(int id, double balance) {
        super(id,balance);
    }
}

/*
* Nazwa: account (klasa abstrakcyjna)
* Parametry: id, balance
* Opis: Reprezentuje ogólne konto bankowe oraz podstawowe operacje finansowe
* */
package bank.model;

public class Account {
    protected int id;
    protected double balance;

    /*
    * Nazwa: account (konstruktor)
    * Parametry: id, balance
    * Opis: Inicjalizuje konto bankowe z podanym indentyfikatorem i saldem
    * */

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }
    /*
     * Nazwa: deposit
     * Parametry: ammount
     * Opis: Dodaje środki do salda konta
     * */
    public void deposit(double amount) {
        balance += amount;
    }
    /*
     * Nazwa: withdraw
     * Parametry: amount
     * Opis: Wypłaca środki z konta, jeśli dostępne saldo jest wystarczające
     * */
    public void withdraw(double amount) {
        if(balance >= amount) {
            balance -= amount;
        }
    }
    /*
     * Nazwa: getBalance
     * Parametry: brak
     * Opis: Zwraca aktulne saldo
     * */
    public double getBalance() {
        return balance;
    }
    /*
     * Nazwa: getId
     * Parametry: brak
     * Opis: Zwraca indetyfikator konta
     * */
    public int getId() {
        return id;
    }
}

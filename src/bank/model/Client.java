/*
 * Nazwa: Client
 * Parametry: id, name
 * Opis: Reprezentuje klienta banku oraz przechgowuje jego konta
 * */
package bank.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String name;
    private List<Account> accounts;
    /*
     * Nazwa: Client (konstruktor)
     * Parametry: id, name
     * Opis: Tworzy nowego klienta z przypisanym identyfikatorem i nazwa
     * */
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        this.accounts = new ArrayList<>();
    }
    /*
     * Nazwa: addAccount
     * Parametry: account
     * Opis: Dodaje konto do listy kont klienta
     * */
    public void addAccount(Account account) {
        accounts.add(account);
    }
    /*
     * Nazwa: getAccounts
     * Parametry: brak
     * Opis: Zwraca listę kont klienta
     * */
    public List<Account> getAccounts() {
        return accounts;
    }
}

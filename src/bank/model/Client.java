package bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Nazwa: Client
 * Parametry: id, fullName
 * Opis: Klasa reprezentująca klienta banku, który może posiadać wiele kont.
 */
public class Client {

    private static int nextId = 1;
    private final int id;
    private String fullName;
    private final List<Account> accounts;

    /*
     * Nazwa: Client (konstruktor)
     * Parametry: fullName
     * Opis: Tworzy nowego klienta i nadaje mu automatyczny identyfikator.
     */
    public Client(String fullName) {
        this(nextId, fullName);
    }

    /*
     * Nazwa: Client (konstruktor)
     * Parametry: id, fullName
     * Opis: Tworzy klienta z jawnie podanym identyfikatorem, używane głównie przy wczytywaniu danych.
     */
    public Client(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.accounts = new ArrayList<>();

        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    /*
     * Nazwa: addAccount
     * Parametry: account
     * Opis: Dodaje konto do listy kont klienta.
     */
    public void addAccount(Account account) {
        if (account != null) {
            accounts.add(account);
        }
    }

    /*
     * Nazwa: getId
     * Parametry: brak
     * Opis: Zwraca identyfikator klienta.
     */
    public int getId() {
        return id;
    }

    /*
     * Nazwa: getFullName
     * Parametry: brak
     * Opis: Zwraca pełne imię i nazwisko klienta.
     */
    public String getFullName() {
        return fullName;
    }

    /*
     * Nazwa: setFullName
     * Parametry: fullName
     * Opis: Umożliwia zmianę imienia i nazwiska klienta.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /*
     * Nazwa: getAccounts
     * Parametry: brak
     * Opis: Zwraca niezmienialną listę kont klienta.
     */
    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    /*
     * Nazwa: toString
     * Parametry: brak
     * Opis: Zwraca tekstową reprezentację klienta.
     */
    @Override
    public String toString() {
        return "Client{id=" + id +
                ", fullName='" + fullName + '\'' +
                ", accounts=" + accounts.size() +
                '}';
    }
}
package bank.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String name;
    private List<Account> accounts;

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}

package bank;

import bank.model.*;

public class Main {
    public static void main(String[] args) {

        Client client = new Client(1,"Jan Kowalski");
        Account account = new SavingsAccount(1, 1000);

        client.addAccount(account);

        account.deposit(500);
        account.withdraw(200);

        System.out.println("Saldo: " + account.getBalance());
    }
}

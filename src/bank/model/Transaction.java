/*
 * Nazwa: Transaction
 * Parametry: id, amount, type
 * Opis: Reprezentuje operacje finansowa wykonwywana na koncie
 * */
package bank.model;

public class Transaction {
    private int id;
    private double amount;
    private String type;
    /*
     * Nazwa: Transaction (konstruktor)
     * Parametry: id, amount, type
     * Opis: Tworzy nowa transakcje o koreślonym typie i kwocie
     * */
    public Transaction(int id, double amount, String type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }
    /*
     * Nazwa: getAmount
     * Parametry: brak
     * Opis: Zwraca kwotę transakcji
     * */
    public double getAmount() {
        return amount;
    }
    /*
     * Nazwa: getType
     * Parametry: brak
     * Opis: Zwraca typ transakcji
     * */
    public String getType() {
        return type;
    }
}

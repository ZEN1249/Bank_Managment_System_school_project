package bank.service;

import bank.data.FileManager;

/*
 * Nazwa: AutoSaveThread
 * Parametry: bankService, fileManager, clientsFile, accountsFile, cardsFile, transactionsFile
 * Opis: Wątek odpowiedzialny za cykliczne automatyczne zapisywanie danych do plików CSV.
 */
public class AutoSaveThread extends Thread {
    private final BankService bankService;
    private final FileManager fileManager;
    private final String clientsFile;
    private final String accountsFile;
    private final String cardsFile;
    private final String transactionsFile;
    private volatile boolean running = true;

    /*
     * Nazwa: AutoSaveThread (konstruktor)
     * Parametry: bankService, fileManager, clientsFile, accountsFile, cardsFile, transactionsFile
     * Opis: Tworzy wątek automatycznego zapisu danych.
     */
    public AutoSaveThread(BankService bankService, FileManager fileManager, String clientsFile, String accountsFile, String cardsFile, String transactionsFile) {
        this.bankService = bankService;
        this.fileManager = fileManager;
        this.clientsFile = clientsFile;
        this.accountsFile = accountsFile;
        this.cardsFile = cardsFile;
        this.transactionsFile = transactionsFile;
        setDaemon(true);
    }

    /*
     * Nazwa: run
     * Parametry: brak
     * Opis: Wykonuje cykliczny zapis danych co 30 sekund.
     */
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(30000);
                bankService.saveData(fileManager, clientsFile, accountsFile, cardsFile, transactionsFile);
                System.out.println("AutoSave: zapisano dane do plików.");
            } catch (InterruptedException e) {
                running = false;
                Thread.currentThread().interrupt();
            }
        }
    }

    /*
     * Nazwa: stopSaving
     * Parametry: brak
     * Opis: Zatrzymuje działanie wątku automatycznego zapisu.
     */
    public void stopSaving() {
        running = false;
        interrupt();
    }
}
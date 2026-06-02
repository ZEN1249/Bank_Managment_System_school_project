package bank.model;

/*
 * Nazwa: TransactionType
 * Parametry: brak
 * Opis: Enum definiujący typy operacji bankowych rejestrowanych w systemie.
 */
public enum TransactionType {
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    INTEREST,
    FEE
}
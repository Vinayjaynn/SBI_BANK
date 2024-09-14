
package BANKS;
public abstract class BankAccount {
    private String accountHolderName;
    private int accountNumber;
    protected double balance; // Balance is protected so that subclasses can access it
    private int pin; // PIN for account security

    public BankAccount(String accountHolderName, int accountNumber, double initialBalance, int pin) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        setPin(pin); // Validate and set the PIN
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Validate that PIN is exactly 4 digits
    private boolean validatePin(int pin) {
        return pin >= 1000 && pin <= 9999;
    }

    // Set the PIN if valid
    public void setPin(int pin) {
        if (validatePin(pin)) {
            this.pin = pin;
        } else {
            throw new IllegalArgumentException("Invalid PIN. Please enter a 4 digit pin.");
        }
    }

    public boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance+" Rs");
    }
}

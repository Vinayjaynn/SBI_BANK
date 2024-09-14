package BANKS;
import java.util.*;
class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private Random random = new Random();

    // Generates a unique account number for each account
    public int generateUniqueAccountNumber() {
        int accountNumber;
        do {
            accountNumber = 100000 + random.nextInt(900000); // Generate 6-digit account number
        } while (findAccount(accountNumber) != null); // Ensure it's unique
        return accountNumber;
    }

    public void createSavingsAccount(String accountHolderName, double initialBalance, int pin) {
        try {
            int accountNumber = generateUniqueAccountNumber();
            BankAccount newAccount = new SavingsAccount(accountHolderName, accountNumber, initialBalance, pin);
            accounts.add(newAccount);
            System.out.println("Savings Account created successfully! Account Number: " + accountNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Handle the invalid PIN exception
        }
    }

    public void createCurrentAccount(String accountHolderName, double initialBalance, int pin) {
        try {
            int accountNumber = generateUniqueAccountNumber();
            BankAccount newAccount = new CurrentAccount(accountHolderName, accountNumber, initialBalance, pin);
            accounts.add(newAccount);
            System.out.println("Current Account created successfully! Account Number: " + accountNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Handle the invalid PIN exception
        }
    }

    public BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public boolean validatePin(BankAccount account, int enteredPin) {
        if (account != null && account.verifyPin(enteredPin)) {
            return true;
        } else {
            System.out.println("Invalid PIN. Please try again.");
            return false;
        }
    }

    public void depositToAccount(int accountNumber, double amount, int enteredPin) {
        BankAccount account = findAccount(accountNumber);
        if (validatePin(account, enteredPin)) {
            account.deposit(amount);
        }
    }

    public void withdrawFromAccount(int accountNumber, double amount, int enteredPin) {
        BankAccount account = findAccount(accountNumber);
        if (validatePin(account, enteredPin)) {
            account.withdraw(amount);
        }
    }

    public void displayAccountInfo(int accountNumber, int enteredPin) {
        BankAccount account = findAccount(accountNumber);
        if (validatePin(account, enteredPin)) {
            account.displayAccountInfo();
        }
    }

    // New: Balance Inquiry
    public void inquireBalance(int accountNumber, int enteredPin) {
        BankAccount account = findAccount(accountNumber);
        if (validatePin(account, enteredPin)) {
            System.out.println("Your current balance is: " + account.getBalance());
        }
    }
}
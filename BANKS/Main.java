package BANKS;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\nBank Management System");
                System.out.println("1. Create Savings Account");
                System.out.println("2. Create Current Account");
                System.out.println("3. Deposit");
                System.out.println("4. Withdraw");
                System.out.println("5. Display Account Info");
                System.out.println("6. Balance Inquiry");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");
                
                int choice = getValidInt(scanner);

                switch (choice) {
                    case 1:
                        System.out.print("Enter account holder name: ");
                        scanner.nextLine();  // Consume newline
                        String savingsHolderName = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double savingsInitialBalance = getValidDouble(scanner);
                        System.out.print("Set a 4-digit PIN: ");
                        int savingsPin = getValidPin(scanner);
                        try {
                            bank.createSavingsAccount(savingsHolderName, savingsInitialBalance, savingsPin);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage()); // Handle the invalid PIN exception
                        }
                        break;
                    case 2:
                        System.out.print("Enter account holder name: ");
                        scanner.nextLine();  // Consume newline
                        String currentHolderName = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double currentInitialBalance = getValidDouble(scanner);
                        System.out.print("Set a 4-digit PIN: ");
                        int currentPin = getValidPin(scanner);
                        try {
                            bank.createCurrentAccount(currentHolderName, currentInitialBalance, currentPin);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage()); // Handle the invalid PIN exception
                        }
                        break;
                    case 3:
                        System.out.print("Enter account number: ");
                        int depositAccountNumber = getValidInt(scanner);
                        BankAccount depositAccount = bank.findAccount(depositAccountNumber);
                        if (depositAccount != null) {
                            System.out.print("Enter PIN: ");
                            int depositPin = getValidPin(scanner);
                            if (depositAccount.verifyPin(depositPin)) {
                                System.out.print("Enter deposit amount: ");
                                double depositAmount = getValidDouble(scanner);
                                bank.depositToAccount(depositAccountNumber, depositAmount, depositPin);
                            } else {
                                System.out.println("Invalid PIN.");
                            }
                        } else {
                            System.out.println("Account not found. Verify the account number or create an account first.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter account number: ");
                        int withdrawAccountNumber = getValidInt(scanner);
                        BankAccount withdrawAccount = bank.findAccount(withdrawAccountNumber);
                        if (withdrawAccount != null) {
                            System.out.print("Enter PIN: ");
                            int withdrawPin = getValidPin(scanner);
                            if (withdrawAccount.verifyPin(withdrawPin)) {
                                System.out.print("Enter withdrawal amount: ");
                                double withdrawAmount = getValidDouble(scanner);
                                bank.withdrawFromAccount(withdrawAccountNumber, withdrawAmount, withdrawPin);
                            } else {
                                System.out.println("Invalid PIN.");
                            }
                        } else {
                            System.out.println("Account not found. Verify the account number or create an account first.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter account number: ");
                        int displayAccountNumber = getValidInt(scanner);
                        BankAccount displayAccount = bank.findAccount(displayAccountNumber);
                        if (displayAccount != null) {
                            System.out.print("Enter PIN: ");
                            int displayPin = getValidPin(scanner);
                            if (displayAccount.verifyPin(displayPin)) {
                                bank.displayAccountInfo(displayAccountNumber, displayPin);
                            } else {
                                System.out.println("Invalid PIN.");
                            }
                        } else {
                            System.out.println("Account not found. Verify the account number or create an account first.");
                        }
                        break;
                    case 6:
                        System.out.print("Enter account number: ");
                        int inquiryAccountNumber = getValidInt(scanner);
                        BankAccount inquiryAccount = bank.findAccount(inquiryAccountNumber);
                        if (inquiryAccount != null) {
                            System.out.print("Enter PIN: ");
                            int inquiryPin = getValidPin(scanner);
                            if (inquiryAccount.verifyPin(inquiryPin)) {
                                bank.inquireBalance(inquiryAccountNumber, inquiryPin);
                            } else {
                                System.out.println("Invalid PIN.");
                            }
                        } else {
                            System.out.println("Account not found. Verify the account number or create an account first.");
                        }
                        break;
                    case 7:
                        System.out.println("Thank you for using the Bank Management System!");
                        return; // Exits the program
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } finally {
            // Ensure the Scanner is closed
            scanner.close();
        }
    }

    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextInt();
    }

    private static double getValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextDouble();
    }

    private static int getValidPin(Scanner scanner) {
        int pin = getValidInt(scanner);
        while (pin < 1000 || pin > 9999) {
            System.out.println("\"Invalid PIN. Please enter a 4 digit pin.");
            pin = getValidInt(scanner);
        }
        return pin;
    }
}
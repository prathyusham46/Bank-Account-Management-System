
import java.util.Scanner;

// Custom exception class for insufficient funds
class InsufficientFundException extends Exception {
    public InsufficientFundException(String message) {
        super(message);
    }
}

// Bank account class
class BankAccount {
    private final int accountNumber;
    private final String accountHolder;
    private double balance;
    private final double minimumBalance;

    // Constructor
    public BankAccount(int accountNumber, String accountHolder, double balance, double minimumBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.minimumBalance = minimumBalance;
    }

    // Deposit money into account
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    // Withdraw money from account
    public void withdraw(double amount) throws InsufficientFundException {
        if (balance - amount < minimumBalance) {
            throw new InsufficientFundException("Insufficient funds to complete transaction");
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: " + balance);
    }

    // Check account balance
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int accountNumber;
        String accountHolder;
        double balance, minimumBalance, amount;

        // Get account information from user
        System.out.print("Enter account number: ");
        accountNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter account holder: ");
        accountHolder = scanner.nextLine();
        System.out.print("Enter opening balance: ");
        balance = scanner.nextDouble();
        System.out.print("Enter minimum balance: ");
        minimumBalance = scanner.nextDouble();

        // Create BankAccount object
        BankAccount account = new BankAccount(accountNumber, accountHolder, balance, minimumBalance);

        // Bank operations loop
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check balance");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    amount = scanner.nextDouble();
                    account.deposit(amount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    amount = scanner.nextDouble();
                    try {
                        account.withdraw(amount);
                    } catch (InsufficientFundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for banking with us!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
            }
        }
    }
}
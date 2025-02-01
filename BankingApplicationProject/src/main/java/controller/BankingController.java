package controller;
import java.util.Scanner;
import org.hibernate.Session;
import service.BankingService;

public class BankingController {

    // Single scanner instance
    private static final Scanner scanner = new Scanner(System.in);
    private final BankingService bankingService;
    private final Session session;

    public BankingController(Session session) {
        this.session = session;
        this.bankingService = new BankingService();
    }

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nBanking Application Menu");
            System.out.println("1. Create User");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.println("Enter Option:");

            int choice = getUserInput();
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int getUserInput() {
        return scanner.nextInt();
    }

    private void createUser() {
        System.out.println("Enter user name: ");
        scanner.nextLine(); // Consume the leftover newline character from previous input
        String name = scanner.nextLine(); 

        System.out.println("Enter email: ");
        String email = scanner.next();

        bankingService.createUser(session, name, email);
    }

    private void createAccount() {
        System.out.println("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter account type (e.g., Savings, Checking): ");
        String accountType = scanner.nextLine();

        System.out.println("Enter initial balance: ");
        double balance = scanner.nextDouble();

        bankingService.createAccount(session, userId, accountType, balance);
    }

    private void deposit() {
        System.out.print("Enter account number: ");
         String accountNumber = scanner.next();

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        bankingService.deposit(session, accountNumber, amount);
    }

    private void withdraw() {
        System.out.println("Enter account number: ");
        String accountNumber = scanner.next();

        System.out.println("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        bankingService.withdraw(session, accountNumber, amount);
    }

    private void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();

        bankingService.checkBalance(session, accountNumber);
    }
}

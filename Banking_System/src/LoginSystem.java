import java.util.Scanner;

public class LoginSystem {
    private final Scanner scanner = new Scanner(System.in);
    private final LoginManager loginManager = LoginManager.getInstance();
    private final AccountManager accountManager = new AccountManager();
    private final Menu menu = new Menu();

    public void start() {
        boolean value = true;
        while (value) {
            System.out.println("\n==== Welcome to the Banking System ====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    handleUserLogin();
                    break;
                case 3:
                    System.out.println("Exiting the banking system!");
                    value = false;
                    break;
                default:
                    System.out.println("Invalid Input. Try again!");
            }
        }
    }

    private void handleAdminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        if (loginManager.checkAdminLogin(username, password)) {
            System.out.println("Admin login successful.");
            menu.handleAdminActions();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private void handleUserLogin() {
        System.out.println("\n1. Login");
        System.out.println("2. Create New Account");
        System.out.print("Enter your choice (1 or 2): ");
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        switch (userChoice) {
            case 1:
                System.out.print("Enter your account number: ");
                String accountNumber = scanner.nextLine();
                System.out.print("Enter your PIN: ");
                String pin = scanner.nextLine();

                if (loginManager.checkUserLogin(accountNumber, pin)) {
                    System.out.println("User login successful.");
                    menu.handleUserActions();  // account number is stored centrally now
                } else {
                    System.out.println("Invalid credentials.");
                }
                break;
            case 2:
                System.out.print("Enter your full name: ");
                String holderName = scanner.nextLine();
                System.out.print("Enter your desired PIN: ");
                String pinCode = scanner.nextLine();
                BankAccount newAccount = accountManager.createAccount(holderName, pinCode);
                loginManager.setLoggedInAccountNumber(newAccount.accountNumber);
                System.out.println("Account created successfully!");
                menu.handleUserActions();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private final LoginManager loginManager = LoginManager.getInstance();
    private final UserOperations userOperations = new UserOperations();
    private final AdminOperations adminOperations = new AdminOperations();

    public void handleUserActions() {
        boolean exit = false;
        String accountNumber = loginManager.getLoggedInAccountNumber();

        while (!exit) {
            showUserMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userOperations.viewBalance(accountNumber);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    userOperations.deposit(accountNumber, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdraw amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    userOperations.withdraw(accountNumber, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient account number: ");
                    String toAccount = scanner.nextLine();
                    System.out.print("Enter transfer amount: ₹");
                    double transferAmount = scanner.nextDouble();
                    userOperations.transfer(accountNumber, toAccount, transferAmount);
                    break;
                case 5:
                    userOperations.viewProfile(accountNumber);
                    break;
                case 6:
                    System.out.println("Logging out...");
//                    loginManager.logout();  // Clear session
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void handleAdminActions() {
        boolean exit = false;

        while (!exit) {
            showAdminMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminOperations.listAllAccounts();
                    break;
                case 2:
                    System.out.print("Enter account number to delete: ");
                    String accountToDelete = scanner.nextLine();
                    adminOperations.deleteAccount(accountToDelete);
                    break;
                case 3:
                    System.out.print("Enter account number to search: ");
                    String accountToSearch = scanner.nextLine();
                    adminOperations.searchAccount(accountToSearch);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showUserMenu() {
        System.out.println("User Menu:");
        System.out.println("1. View balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer money");
        System.out.println("5. View profile");
        System.out.println("6. Logout");
        System.out.print("Please select an option: ");
    }

    private void showAdminMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. List all accounts");
        System.out.println("2. Delete an account");
        System.out.println("3. Search an account");
        System.out.println("4. Logout");
        System.out.print("Please select an option: ");
    }
}

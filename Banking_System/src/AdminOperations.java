//import java.util.List;

public class AdminOperations {
    private final AccountManager accountManager = new AccountManager();

    public void listAllAccounts() {
       accountManager.printAllAccounts();
    }

    public void deleteAccount(String accountNumber) {
       accountManager.deleteAccount(accountNumber);
    }

    public void searchAccount(String accountNumber) {
        BankAccount account = accountManager.loadAccount(accountNumber);
        if (account != null) {
            System.out.println("Account found:\n" + account);
        } else {
            System.out.println("Account not found.");
        }
    }
}

public class UserOperations {
    private final AccountManager accountManager = new AccountManager();
    private final LoginManager loginManager = new LoginManager();
    public String Number;

    public void deposit(String accountNumber, double amount) {
        loginManager.verifyPin(accountNumber);
       accountManager.depositToAccount(accountNumber, amount);
    }

    public void withdraw(String accountNumber, double amount) {
        loginManager.verifyPin(accountNumber);
        accountManager.withdrawFromAccount(accountNumber, amount);
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
//        // Check if user is trying to transfer from someone else's account
//        if (!fromAccount.equals(loginManager.getLoggedInAccountNumber())) {
//            System.out.println("Unauthorized transfer attempt. You can only transfer from your own account.");
//            return;
//        }
        System.out.println("Money transferring from account number : "+ fromAccount);
        // Prevent transferring to the same account
        if (fromAccount.equals(toAccount)) {
            System.out.println("You cannot transfer money to the same account.");
            return;
        }

        // Proceed only if PIN is correct
        if (!loginManager.verifyPin(fromAccount)) return;

        accountManager.transferBetweenAccounts(fromAccount, toAccount, amount);
    }


    public void viewBalance(String accountNumber) {
        BankAccount acc = accountManager.loadAccount(accountNumber);
        if (acc != null) {
            System.out.println("Current Balance: ₹" + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void viewProfile(String accountNumber) {
        BankAccount acc = accountManager.loadAccount(accountNumber);
        if (acc != null) {
            System.out.println("Your Profile:");
            System.out.println(acc);
        } else {
            System.out.println("Account not found.");
        }
    }
}

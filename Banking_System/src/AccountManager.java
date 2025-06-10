import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;

public class AccountManager {

    private static final String FILE_NAME = "accounts.json";

    // Method to create a new account
    public BankAccount createAccount(String holderName, String pin) {
        BankAccount newAccount = new BankAccount(holderName, pin);
        newAccount.saveAccount(); // Save the newly created account
// ✅ Increment totalAccounts here
        try {
            File file = new File(FILE_NAME);
            JSONObject allAccounts = new JSONObject();

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();
                allAccounts = new JSONObject(content.toString());
            }

            int total = allAccounts.optInt("totalAccounts", 0);
            allAccounts.put("totalAccounts", total + 1);

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.write(allAccounts.toString(4));
            writer.close();

        } catch (IOException | JSONException e) {
            System.out.println("Error updating totalAccounts: " + e.getMessage());
        }

        return newAccount;
    }

    // Method to save an account (calls the BankAccount's save method)
    public void saveAccount(BankAccount account) {
        account.saveAccount();
    }

    // Method to load an account by account number
    public BankAccount loadAccount(String accountNumber) {
        BankAccount account = new BankAccount(accountNumber); // No new account number is created
        if (account.loadAccount()) {
            return account;
        } else {
            return null;
        }
    }

    // Method to handle deposit
    public void depositToAccount(String accountNumber, Double amount) {
        BankAccount account = loadAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        }
    }

    // Method to handle withdrawal
    public void withdrawFromAccount(String accountNumber, Double amount) {
        BankAccount account = loadAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        }
    }

    // Method to handle transfer
    public void transferBetweenAccounts(String fromAccountNumber, String toAccountNumber, Double amount) {
        BankAccount fromAccount = loadAccount(fromAccountNumber);
        BankAccount toAccount = loadAccount(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            System.out.println("Invalid source or destination account.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }

        if (fromAccount.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        fromAccount.transfer(amount, toAccount);

    }

    public void deleteAccount(String accountNumber) {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();

                JSONObject jsonAccounts = new JSONObject(content.toString());

                // Remove the account from the JSON object
                jsonAccounts.remove(accountNumber);

                // Write the updated JSON back to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
                writer.write(jsonAccounts.toString(4)); // Pretty print with 4 spaces
                writer.close();

                System.out.println("Account deleted successfully!");
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }

    // Helper method to load the total number of accounts from the file
    public static int loadTotalAccountCount() {
        return BankAccount.loadTotalAccountCount(); // Call the static method from BankAccount
    }


    // Method to print all accounts from the JSON file
    public void printAllAccounts() {
        try {
            // Read the JSON file content into a StringBuilder
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("No accounts found.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            // Parse the content into a JSONObject
            JSONObject accountsJson = new JSONObject(content.toString());

            // Get the total accounts count
            int totalAccounts = accountsJson.getInt("totalAccounts");

            if (totalAccounts == 0) {
                System.out.println("No accounts available.");
                return;
            }

            // Loop through all account numbers (excluding the metadata "totalAccounts")
            for (String accountNumber : accountsJson.keySet()) {
                if (accountNumber.equals("totalAccounts")) {
                    continue; // Skip the totalAccounts metadata key
                }

                JSONObject account = accountsJson.getJSONObject(accountNumber);
                System.out.println("Account Number: " + accountNumber);
                System.out.println("Name: " + account.getString("holderName"));
                System.out.println("Balance: " + account.getDouble("balance"));
                System.out.println("---------------------------");
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error reading accounts from file: " + e.getMessage());
        }
    }

}

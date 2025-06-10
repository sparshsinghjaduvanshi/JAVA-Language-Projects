import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;

public class BankAccount {
    public String accountNumber;
    private String holderName;
    private String pin;
    private Double balance;
    private static int accountCounter = 0;

    private static final String FILE_NAME = "accounts.json"; // File where account data is stored

    // Constructor
    public BankAccount(String holderName, String pin) {
        this.accountNumber = generateAccountNumber(); // Account number is automatically generated
        this.holderName = holderName;                   // Name of the account holder
        this.pin = pin;                                 // Account PIN for security
        this.balance = 0.0;                             // Default balance is 0.0
    }

    // Constructor for loading an existing account (no new account number is generated)
    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Generates a unique account number based on the number of accounts already created
    private String generateAccountNumber() {
        return "ACCT" + loadTotalAccountCount(); // Account number will be something like ACCT1, ACCT2, etc.
    }

    // Method to load account data from the JSON file
    public boolean loadAccount() {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                // Use FileReader and BufferedReader to read file content
                BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();

                JSONObject jsonAccounts = new JSONObject(content.toString());

                // Check if the account exists in the JSON file by account number
                if (jsonAccounts.has(this.accountNumber)) {
                    JSONObject accountData = jsonAccounts.getJSONObject(this.accountNumber);
                    this.holderName = accountData.getString("holderName");
                    this.pin = accountData.getString("pin");
                    this.balance = accountData.getDouble("balance");
                    return true;
                }
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error loading account: " + e.getMessage());
        }
        return false;
    }

    // Method to save the account details to the JSON file
    public void saveAccount() {
        try {
            // Load existing accounts from the file
            JSONObject allAccounts = new JSONObject();
            File file = new File(FILE_NAME);
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

            // Create the JSON object for the current account
            JSONObject accountJson = new JSONObject();
            accountJson.put("holderName", this.holderName);
            accountJson.put("pin", this.pin);
            accountJson.put("balance", this.balance);

            // Add or update the account in the JSON object
            allAccounts.put(this.accountNumber, accountJson);


            // Write the updated JSON back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.write(allAccounts.toString(4));
            writer.close();

            System.out.println("Account saved successfully!");
            System.out.println("New Account Number: " + this.accountNumber); // newly created account number
        } catch (IOException | JSONException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

    // Static method to load the total account count from the file
    public static int loadTotalAccountCount() {
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

                if (jsonAccounts.has("totalAccounts")) {
                    return jsonAccounts.getInt("totalAccounts"); // Retrieve total account count
                }
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error loading total account count: " + e.getMessage());
        }
        return 0; // Return 0 if file doesn't exist or error occurs
    }

    // Getters for account information
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getPin() {
        return pin;
    }

    public Double getBalance() {
        return balance;
    }

    // Override toString method to print account details
    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\n" +
                "Account Holder: " + holderName + "\n" +
                "Balance: " + balance + "\n" +
                "PIN: " + pin;
    }

    // Deposit method to add money to the account
    public void deposit(Double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited " + amount + " successfully. New balance: " + this.balance);
            saveAccount();

        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdraw method to withdraw money from the account
    public void withdraw(Double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrew " + amount + " successfully. New balance: " + this.balance);
            saveAccount();
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }

    // Transfer method to transfer money between two accounts
    public void transfer(Double amount, BankAccount toAccount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            toAccount.deposit(amount); // Deposit the transferred amount to the target account
            System.out.println("Transferred " + amount + " to account " + toAccount.getAccountNumber() + ".");
            saveAccount(); // Save the current account after transferring
            toAccount.saveAccount(); // Save the target account after receiving the transfer
        } else {
            System.out.println("Insufficient balance or invalid transfer amount.");
        }
    }
}

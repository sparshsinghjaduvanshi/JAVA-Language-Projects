import org.json.JSONObject;
import org.json.JSONException;
//import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.Scanner;

public class LoginManager {

    private static final String ADMIN_FILE = "admin.json";
    private static final String USER_FILE = "accounts.json";

    public String loggedInAccountNumber;  // Store the logged-in user's account number
    public static LoginManager instance;

    private final Scanner scanner = new Scanner(System.in);

    // Private constructor for singleton pattern
    protected LoginManager() {}


    // Singleton instance getter
    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    // Admin login verification
    public boolean checkAdminLogin(String username, String password) {
        try {
            File file = new File(ADMIN_FILE);
            if (!file.exists())
                return false;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            JSONObject admins = new JSONObject(content.toString());
            JSONObject adminData = admins.getJSONObject("admin"); // Access nested object

            // Now check credentials
            if(adminData.getString("username").equals(username) && adminData.getString("password").equals(password)){
                return true;
            }

        } catch (IOException | JSONException e) {
            System.out.println("Error during admin login: " + e.getMessage());
        }
        return false;
    }

    // User login verification
    public boolean checkUserLogin(String accountNumber, String pin) {
        AccountManager accountManager = new AccountManager();
        BankAccount account = accountManager.loadAccount(accountNumber);
        if (account != null && account.getPin().equals(pin)) {
            setLoggedInAccountNumber(accountNumber);
            loggedInAccountNumber = accountNumber; // Store the logged-in account number

            System.out.println("CheckUser       "+loggedInAccountNumber);  //debugiing
            return true;
        }
        return false;
    }

    // Method to verify PIN before allowing certain operations
    public boolean verifyPin(String accountNumber) {
        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();
        AccountManager accountManager = new AccountManager();
        BankAccount account = accountManager.loadAccount(accountNumber);

        // Check if the entered PIN matches the stored PIN
        if (account != null && account.getPin().equals(enteredPin)) {
            return true;
        } else {
            System.out.println("Invalid PIN. Please try again.");
            return false;
        }
    }

    // Getter for the logged-in account number
    public void setLoggedInAccountNumber(String accountNumber) {
        loggedInAccountNumber = accountNumber;

        System.out.println("Set the value : "+ loggedInAccountNumber ); //debugging
    }

    // Getter for the logged-in account number
    public String getLoggedInAccountNumber() {
        System.out.println("Getter: "+loggedInAccountNumber);
        return loggedInAccountNumber;
    }
}

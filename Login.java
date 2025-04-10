import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseConnector dbConnector = new DatabaseConnector();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (operator/customer): ");
        String role = scanner.nextLine();

        boolean loginSuccessful = dbConnector.loginUser(username, password, role);
        if (loginSuccessful) {
            System.out.println("Login successful! Welcome, " + role + ".");
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }

        scanner.close();
    }
}

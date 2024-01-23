import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RegisterForm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Registration Form");

        // Collect user input
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter firstname: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter lastname: ");
        String lastname = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/mktest";
        String dbUsername = "root";
        String dbPassword = "";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {

                // SQL query to insert a new user
                String insertQuery = "INSERT INTO users (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";

                // Create a PreparedStatement
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    // Set the parameters for the query
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, firstname);
                    preparedStatement.setString(3, lastname);
                    preparedStatement.setString(4, password);

                    // Execute the query
                    int rowsAffected = preparedStatement.executeUpdate();

                    System.out.println("rowsAffected");
                    System.out.println(rowsAffected);

                    if (rowsAffected > 0) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("Failed to register user.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

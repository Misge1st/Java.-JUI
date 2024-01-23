/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ABC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Registration Form");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter firstname: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter lastname: ");
        String lastname = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Choose Department:");
        System.out.println("1. [ ] Software Engineering");
        System.out.println("2. [ ] Computer Science");
        System.out.println("3. [ ] communication");

        System.out.print("Enter the number corresponding to your department: ");
        int departmentChoice = scanner.nextInt();

        String department = processDepartmentChoice(departmentChoice);

        // Markdown-style gender selection
        System.out.println("Choose Gender:");
        System.out.println("1. [ ] Male");
        System.out.println("2. [ ] Female");

        System.out.print("Tick the box next to your gender (1 for Male, 2 for Female): ");
        int genderChoice = scanner.nextInt();

        String gender = processGenderChoice(genderChoice);

        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/myDB";
        String dbUsername = "root";
        String dbPassword = "root";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {

                // SQL query to insert a new user
                String insertQuery = "INSERT INTO users (username, firstname, lastname, password, department, gender) VALUES (?, ?, ?, ?, ?, ?)";

              
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, firstname);
                    preparedStatement.setString(3, lastname);
                    preparedStatement.setString(4, password);
                    preparedStatement.setString(5, department);
                    preparedStatement.setString(6, gender);

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

    private static String processDepartmentChoice(int choice) {
        switch (choice) {
            case 1:
                return "Software Engineering";
            case 2:
                return "Computer Science";
            case 3:
                return "communication";
            default:
                return "Unknown";
        }
    }

    private static String processGenderChoice(int choice) {
        switch (choice) {
            case 1:
                return "Male";
            case 2:
                return "Female";
            default:
                return "Unknown";
        }
    }
}

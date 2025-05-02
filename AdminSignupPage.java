import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
/**
 * AdminSignupPage class represents the signup interface for creating new administrator accounts
 * in a complaint management system. It provides a graphical user interface with fields for
 * username and password, and buttons for signup and navigating back to the dashboard. The class
 * extends JFrame for window creation and implements ActionListener to handle button click events.
 * New admin credentials are appended to a CSV file after validation.
 */
public class AdminSignupPage extends JFrame implements ActionListener {
    // Instance variables for UI components
    JTextField usernameField;     // Text field for entering username
    JPasswordField passwordField; // Password field for entering password
    JButton signupButton;         // Button to submit signup details
    JButton backButton;           // Button to return to the admin dashboard

    /**
     * Constructor for AdminSignupPage.
     * Initializes the admin signup window, configures its properties, creates UI components
     * (labels, text fields, and buttons), assigns action listeners, and arranges components in a grid layout.
     */
    AdminSignupPage() {
        // Set the title of the window
        setTitle("Admin Signup");
        // Set the size of the window (width: 400px, height: 300px)
        setSize(400, 300);
        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Initialize UI components
        JLabel usernameLabel = new JLabel("Username:"); // Label for username field
        JLabel passwordLabel = new JLabel("Password:"); // Label for password field
        usernameField = new JTextField();               // Text field for username input
        passwordField = new JPasswordField();           // Password field for secure password input
        signupButton = new JButton("Signup");           // Button to initiate signup
        backButton = new JButton("Back");               // Button to navigate back to dashboard

        // Register the current object as the action listener for buttons
        signupButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set the layout to a GridLayout with 4 rows, 2 columns, and 10px gaps
        setLayout(new GridLayout(4, 2, 10, 10));

        // Add components to the layout in the specified order
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signupButton);
        add(backButton);
    }

    /**
     * Handles action events triggered by button clicks.
     * For the signup button, it validates the entered username and password, checks for
     * duplicate usernames in the "admins.csv" file, and appends new credentials to the file.
     * For the back button, it navigates to the admin dashboard. Displays appropriate messages
     * for validation errors or successful signup.
     *
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            // Retrieve entered username and password
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validate that both fields are non-empty
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Check for duplicate username in the admins.csv file
            try (Scanner scanner = new Scanner(new File("admins.csv"))) {
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    // Verify if the username already exists (case-insensitive)
                    if (data.length >= 1 && data[0].equalsIgnoreCase(username)) {
                        JOptionPane.showMessageDialog(this, "Username already taken!");
                        return;
                    }
                }
            } catch (FileNotFoundException ex) {
                // Ignore if the file does not exist; it will be created during signup
            }

            // Append new admin credentials to the admins.csv file
            try (FileWriter writer = new FileWriter("admins.csv", true)) {
                writer.write(username + "," + password + "\n");
                JOptionPane.showMessageDialog(this, "Signup Successful!");
                // Open the Admin Dashboard and close the signup window
                new AdminDashboard().setVisible(true); // Return to dashboard
                dispose();
            } catch (IOException ex) {
                // Handle file writing errors
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            // Open the Admin Dashboard and close the signup window
            new AdminDashboard().setVisible(true); // Return to dashboard
            dispose();
        }
    }
}
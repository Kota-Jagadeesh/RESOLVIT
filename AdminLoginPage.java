import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
/**
 * AdminLoginPage class represents the login interface for administrators in a complaint management system.
 * It provides a graphical user interface with fields for username and password, and buttons for login and
 * navigating back to the home page. The class extends JFrame for window creation and implements ActionListener
 * to handle button click events. Admin credentials are authenticated by reading from a CSV file.
 */
public class AdminLoginPage extends JFrame implements ActionListener {
    // Instance variables for UI components
    JTextField usernameField;     // Text field for entering username
    JPasswordField passwordField; // Password field for entering password
    JButton loginButton;          // Button to submit login credentials
    JButton backButton;           // Button to return to the home page

    /**
     * Constructor for AdminLoginPage.
     * Initializes the admin login window, configures its properties, creates UI components
     * (labels, text fields, and buttons), assigns action listeners, and arranges components in a grid layout.
     */
    AdminLoginPage() {
        // Set the title of the window
        setTitle("Admin Login");
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
        loginButton = new JButton("Login");             // Button to initiate login
        backButton = new JButton("Back");               // Button to navigate back to home page

        // Register the current object as the action listener for buttons
        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set the layout to a GridLayout with 4 rows, 2 columns, and 10px gaps
        setLayout(new GridLayout(4, 2, 10, 10));

        // Add components to the layout in the specified order
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(backButton);
    }

    /**
     * Handles action events triggered by button clicks.
     * For the login button, it validates the entered username and password against records
     * in the "admins.csv" file. For the back button, it navigates to the home page. Displays appropriate messages for successful or failed login attempts, and handles file-related errors.
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Retrieve entered username and password
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Attempt to read the admins.csv file
            try (Scanner scanner = new Scanner(new File("admins.csv"))) {
                boolean found = false;
                // Skip the header line if it exists
                if (scanner.hasNextLine()) scanner.nextLine();
                // Iterate through each line of the CSV file
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    // Verify if the line has at least two fields and matches the credentials
                    if (data.length >= 2 && data[0].equals(username) && data[1].equals(password)) {
                        found = true;
                        break;
                    }
                }
                // Display appropriate message based on authentication result
                if (found) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    // Open the Admin Dashboard and close the login window
                    new AdminDashboard().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                // Handle file reading errors and display an error message
                JOptionPane.showMessageDialog(this, "Error reading admin data", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            // Open the Home page and close the login window
            new HomePage().setVisible(true);
            dispose();
        }
    }
}
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

/**
 * AdminLoginPage class provides the login interface for admin users.
 * It verifies login credentials from a CSV file and redirects to the admin dashboard upon success.
 */
public class AdminLoginPage extends JFrame implements ActionListener {

    // GUI components for login form
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton backButton;

    /**
     * Constructor initializes the login interface for admins.
     * Sets up the layout and event handlers.
     */
    public AdminLoginPage() {
        // Set the window title and size
        setTitle("Admin Login");
        setSize(400, 300);

        // Close the app on exit and center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create labels and input fields
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // Create buttons
        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        // Add action listeners to buttons
        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        // Use a GridLayout to arrange components in a 4x2 grid
        setLayout(new GridLayout(4, 2, 10, 10));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(backButton);
    }

    /**
     * Handles button clicks for login and back actions.
     *
     * @param e the ActionEvent triggered by user interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Retrieve user inputs
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try (Scanner scanner = new Scanner(new File("admins.csv"))) {
                boolean found = false;

                // Skip the header line if present
                if (scanner.hasNextLine()) scanner.nextLine();

                // Check each line in the CSV for matching credentials
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    if (data.length >= 2 && data[0].equals(username) && data[1].equals(password)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    // Show success and redirect to admin dashboard
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    new AdminDashboard().setVisible(true);
                    dispose(); // Close the login page
                } else {
                    // Show error if credentials are invalid
                    JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                // Handle file-related errors
                JOptionPane.showMessageDialog(this, "Error reading admin data", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            // Go back to the home page
            new HomePage().setVisible(true);
            dispose(); // Close the login page
        }
    }
}
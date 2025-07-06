import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

/**
 * StudentLoginPage class represents the login interface for students in the complaint management system.
 * It provides a graphical user interface with fields for username and password, and buttons for login,
 * signup, and returning to the home page. The class extends JFrame for window creation and implements
 * ActionListener to handle button click events. Student credentials are authenticated by reading from a CSV file.
 */
public class StudentLoginPage extends JFrame implements ActionListener {
    // Instance variables for UI components
    JTextField usernameField;     // Text field for entering username
    JPasswordField passwordField; // Password field for entering password
    JButton loginButton;          // Button to submit login credentials
    JButton signupButton;         // Button to navigate to the signup page
    JButton backButton;           // Button to return to the home page

    /**
     * Constructor for StudentLoginPage.
     * Initializes the student login window, configures its properties, creates UI components
     * (labels, text fields, and buttons), assigns action listeners, and arranges components in a grid layout.
     */
    StudentLoginPage() {
        // Set the title of the window
        setTitle("Student Login");

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
        signupButton = new JButton("Signup");           // Button to navigate to signup page
        backButton = new JButton("Back");               // Button to navigate back to home page

        // Register the current object as the action listener for buttons
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set the layout to a GridLayout with 4 rows, 2 columns, and 10px gaps
        setLayout(new GridLayout(4, 2, 10, 10));

        // Add components to the layout in the specified order
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(signupButton);
        add(backButton);
    }

    /**
     * Handles action events triggered by button clicks.
     * For the login button, it validates the entered username and password against records
     * in the "students.csv" file. For the signup button, it navigates to the signup page.
     * For the back button, it navigates to the home page. Displays appropriate messages
     * for successful or failed login attempts and handles file-related errors.
     *
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Retrieve entered username and password
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Attempt to read the students.csv file
            try (Scanner scanner = new Scanner(new File("students.csv"))) {
                boolean found = false;
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
                    // Open the Student Dashboard and close the login window
                    new StudentDashboard(username).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials!");
                }
            } catch (IOException ex) {
                // Handle file reading errors and print the stack trace
                ex.printStackTrace();
            }
        } else if (e.getSource() == signupButton) {
            // Open the Student Signup page and close the login window
            new StudentSignupPage().setVisible(true);
            dispose();
        } else if (e.getSource() == backButton) {
            // Open the Home page and close the login window
            new HomePage().setVisible(true);
            dispose();
        }
    }
}
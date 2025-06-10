import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

/**
 * StudentSignupPage class represents the signup interface for creating new student accounts
 * in the complaint management system. It provides a graphical user interface with fields for
 * username, password, email, roll number, and branch, and buttons for signup and returning
 * to the home page. The class extends JFrame for window creation and implements ActionListener
 * to handle button click events. New student credentials are appended to a CSV file after validation.
 */
public class StudentSignupPage extends JFrame implements ActionListener {
    // Instance variables for UI components
    JTextField usernameField;     // Text field for entering username
    JTextField emailField;        // Text field for entering email
    JTextField rollNoField;       // Text field for entering roll number
    JTextField branchField;       // Text field for entering branch
    JPasswordField passwordField; // Password field for entering password
    JButton signupButton;         // Button to submit signup details
    JButton backButton;           // Button to return to the home page

    /**
     * Constructor for StudentSignupPage.
     * Initializes the student signup window, configures its properties, creates UI components
     * (labels, text fields, password field, and buttons), assigns action listeners, and arranges
     * components in a grid layout.
     */
    StudentSignupPage() {
        // Set the title of the window
        setTitle("Student Signup");

        // Set the size of the window (width: 400px, height: 400px)
        setSize(400, 400);

        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Initialize UI components
        JLabel usernameLabel = new JLabel("Username:"); // Label for username field
        JLabel passwordLabel = new JLabel("Password:"); // Label for password field
        JLabel emailLabel = new JLabel("Email:");       // Label for email field
        JLabel rollNoLabel = new JLabel("Roll No:");    // Label for roll number field
        JLabel branchLabel = new JLabel("Branch:");     // Label for branch field

        usernameField = new JTextField();               // Text field for username input
        passwordField = new JPasswordField();           // Password field for secure password input
        emailField = new JTextField();                  // Text field for email input
        rollNoField = new JTextField();                 // Text field for roll number input
        branchField = new JTextField();                 // Text field for branch input

        signupButton = new JButton("Signup");           // Button to initiate signup
        backButton = new JButton("Back");               // Button to navigate back to home page

        // Register the current object as the action listener for buttons
        signupButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set the layout to a GridLayout with 6 rows, 2 columns, and 10px gaps
        setLayout(new GridLayout(6, 2, 10, 10));

        // Add components to the layout in the specified order
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(emailLabel);
        add(emailField);
        add(rollNoLabel);
        add(rollNoField);
        add(branchLabel);
        add(branchField);
        add(signupButton);
        add(backButton);
    }

    /**
     * Handles action events triggered by button clicks.
     * For the signup button, it validates the input fields, checks for duplicate usernames
     * in the "students.csv" file, and appends new student details to the file. For the back
     * button, it navigates to the home page. Displays appropriate messages for validation
     * errors, duplicate usernames, or successful signup.
     *
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            // Retrieve entered details
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            String rollNo = rollNoField.getText();
            String branch = branchField.getText();

            // Validate that all fields are non-empty
            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || rollNo.isEmpty() || branch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Check for duplicate username in the students.csv file
            try (Scanner scanner = new Scanner(new File("students.csv"))) {
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    // Verify if the username already exists (case-insensitive)
                    if (data[0].equalsIgnoreCase(username)) {
                        JOptionPane.showMessageDialog(this, "Username already taken!");
                        return;
                    }
                }
            } catch (FileNotFoundException ex) {
                // Ignore if the file does not exist; it will be created during signup
            }

            // Append new student details to the students.csv file
            try (FileWriter writer = new FileWriter("students.csv", true)) {
                writer.write(username + "," + password + "," + email + "," + rollNo + "," + branch + "\n");
                // Display success message
                JOptionPane.showMessageDialog(this, "Signup Successful!");
                // Open the Student Login page and close the signup window
                new StudentLoginPage().setVisible(true);
                dispose();
            } catch (IOException ex) {
                // Handle file writing errors and print the stack trace
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            // Open the Home page and close the signup window
            new HomePage().setVisible(true);
            dispose();
        }
    }
}
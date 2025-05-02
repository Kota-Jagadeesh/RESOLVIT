import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * MainApp class serves as the entry point for the ResolveIt complaint management system.
 * It launches the HomePage window, which provides options for admin and student login.
 */
public class MainApp {
    /**
     * Main method to launch the ResolveIt application.
     * Creates and displays the HomePage window on the Event Dispatch Thread for thread safety.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the HomePage creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new HomePage().setVisible(true);
        });
    }
}

/**
 * HomePage class represents the main menu interface of the ResolveIt application.
 * It provides a graphical user interface with buttons for admin and student login.
 * The class extends JFrame for window creation and implements ActionListener to handle button click events.
 */
class HomePage extends JFrame implements ActionListener {
    // Instance variables for UI components
    JButton adminLoginButton;    // Button for navigating to the admin login page
    JButton studentLoginButton;  // Button for navigating to the student login page

    /**
     * Constructor for HomePage.
     * Initializes the main menu window, configures its properties, creates UI components
     * (heading label and buttons), assigns action listeners, and arranges components button click events.
    */
    HomePage() {
        // Set the title of the window
        setTitle("ResolveIt - Main Menu");

        // Set the size of the window (width: 400px, height: 300px)
        setSize(400, 300);

        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Create a centered heading label with bold Arial font
        JLabel heading = new JLabel("Welcome to ResolveIt", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));

        // Initialize buttons for admin and student login
        adminLoginButton = new JButton("Admin Login");
        studentLoginButton = new JButton("Student Login");

        // Register the current object as the action listener for buttons
        adminLoginButton.addActionListener(this);
        studentLoginButton.addActionListener(this);

        // Set the layout to a GridLayout with 4 rows, 1 column, and 10px gaps
        setLayout(new GridLayout(4, 1, 10, 10));

        // Add components to the layout in the specified order
        add(heading);
        add(adminLoginButton);
        add(studentLoginButton);
    }

    /**
     * Handles action events triggered by button clicks.
     * Navigates to the AdminLoginPage or StudentLoginPage based on the button clicked
     * and closes the current window.
     *
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginButton) {
            // Open the Admin Login page and close the current window
            new AdminLoginPage().setVisible(true);
            dispose();
        } else if (e.getSource() == studentLoginButton) {
            // Open the Student Login page and close the current window
            new StudentLoginPage().setVisible(true);
            dispose();
        }
    }
}
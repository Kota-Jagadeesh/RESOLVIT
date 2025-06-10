import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

/**
 * The {@code ManagerLoginPage} class provides a graphical user interface (GUI) 
 * for category managers to log into the system.
 * 
 * <p>This login screen allows managers to:
 * <ul>
 *   <li>Enter a username and password</li>
 *   <li>Authenticate using data from a CSV file</li>
 *   <li>Navigate back to the main home page</li>
 * </ul>
 *
 * <p>On successful login, the manager is redirected to the {@link ManagerDashboard}.
 * If credentials are invalid, an error message is shown.
 * 
 * <p>The credentials are read from a file named <code>managers.csv</code>, where each line
 * contains a manager's username and password separated by a comma.
 */
public class ManagerLoginPage extends JFrame implements ActionListener {

    /** Field to input username */
    JTextField usernameField;

    /** Field to input password (hidden characters) */
    JPasswordField passwordField;

    /** Button to submit login credentials */
    JButton loginButton;

    /** Button to go back to the home page */
    JButton backButton;

    /**
     * Constructs the manager login interface.
     * Initializes UI components and sets up layout and event listeners.
     */
    public ManagerLoginPage() {
        setTitle("Manager Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Create labels and input fields
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        // Add action listeners
        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        // Arrange components in a grid layout
        setLayout(new GridLayout(4, 2, 10, 10));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(backButton);
    }

    /**
     * Handles button click events for login and back actions.
     *
     * @param e The ActionEvent triggered by user interactions.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Check credentials from CSV file
            try (Scanner scanner = new Scanner(new File("managers.csv"))) {
                boolean found = false;
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    if (data.length >= 2 && data[0].equals(username) && data[1].equals(password)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    new ManagerDashboard(username).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading manager data", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        } else if (e.getSource() == backButton) {
            new HomePage().setVisible(true);
            dispose();
        }
    }
}

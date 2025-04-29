import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;


public class AdminSignupPage extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton signupButton, backButton;

    AdminSignupPage() {
        setTitle("Admin Signup");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        signupButton = new JButton("Signup");
        backButton = new JButton("Back");

        signupButton.addActionListener(this);
        backButton.addActionListener(this);

        setLayout(new GridLayout(4, 2, 10, 10));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signupButton);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            try (Scanner scanner = new Scanner(new File("admins.csv"))) {
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    if (data.length >= 1 && data[0].equalsIgnoreCase(username)) {
                        JOptionPane.showMessageDialog(this, "Username already taken!");
                        return;
                    }
                }
            } catch (FileNotFoundException ex) {
            }

            try (FileWriter writer = new FileWriter("admins.csv", true)) {
                writer.write(username + "," + password + "\n");
                JOptionPane.showMessageDialog(this, "Signup Successful!");
                new AdminDashboard().setVisible(true); // Return to dashboard
                dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            new AdminDashboard().setVisible(true); // Return to dashboard
            dispose();
        }
    }
}
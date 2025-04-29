import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class StudentLoginPage extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, signupButton, backButton;

    StudentLoginPage() {
        setTitle("Student Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        backButton = new JButton("Back");

        loginButton.addActionListener(this);
        signupButton.addActionListener(this);
        backButton.addActionListener(this);

        setLayout(new GridLayout(4, 2, 10, 10));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(signupButton);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try (Scanner scanner = new Scanner(new File("students.csv"))) {
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
                    new StudentDashboard(username).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == signupButton) {
            new StudentSignupPage().setVisible(true);
            dispose();
        } else if (e.getSource() == backButton) {
            new HomePage().setVisible(true);
            dispose();
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class StudentSignupPage extends JFrame implements ActionListener {
    JTextField usernameField, emailField, rollNoField, branchField;
    JPasswordField passwordField;
    JButton signupButton, backButton;

    StudentSignupPage() {
        setTitle("Student Signup");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel rollNoLabel = new JLabel("Roll No:");
        JLabel branchLabel = new JLabel("Branch:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField = new JTextField();
        rollNoField = new JTextField();
        branchField = new JTextField();

        signupButton = new JButton("Signup");
        backButton = new JButton("Back");

        signupButton.addActionListener(this);
        backButton.addActionListener(this);

        setLayout(new GridLayout(6, 2, 10, 10));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            String rollNo = rollNoField.getText();
            String branch = branchField.getText();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || rollNo.isEmpty() || branch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            try (Scanner scanner = new Scanner(new File("students.csv"))) {
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    if (data[0].equalsIgnoreCase(username)) {
                        JOptionPane.showMessageDialog(this, "Username already taken!");
                        return;
                    }
                }
            } catch (FileNotFoundException ex) {
            }

            try (FileWriter writer = new FileWriter("students.csv", true)) {
                writer.write(username + "," + password + "," + email + "," + rollNo + "," + branch + "\n");
                JOptionPane.showMessageDialog(this, "Signup Successful!");
                new StudentLoginPage().setVisible(true);
                dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            new HomePage().setVisible(true);
            dispose();
        }
    }
}
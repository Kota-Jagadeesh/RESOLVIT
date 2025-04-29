import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomePage().setVisible(true);
        });
    }
}

class HomePage extends JFrame implements ActionListener {
    JButton adminLoginButton, studentLoginButton;

    HomePage() {
        setTitle("ResolveIt - Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel("Welcome to ResolveIt", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));

        adminLoginButton = new JButton("Admin Login");
        studentLoginButton = new JButton("Student Login");

        adminLoginButton.addActionListener(this);
        studentLoginButton.addActionListener(this);

        setLayout(new GridLayout(4, 1, 10, 10));
        add(heading);
        add(adminLoginButton);
        add(studentLoginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginButton) {
            new AdminLoginPage().setVisible(true);
            dispose();
        } else if (e.getSource() == studentLoginButton) {
            new StudentLoginPage().setVisible(true);
            dispose();
        }
    }
}
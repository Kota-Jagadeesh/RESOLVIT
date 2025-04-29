import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RaiseComplaintPage extends JFrame implements ActionListener {
    JTextField categoryField, descriptionField;
    JButton submitButton, backButton;
    String studentUsername;

    RaiseComplaintPage(String studentUsername) {
        this.studentUsername = studentUsername;
        setTitle("Raise Complaint");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel categoryLabel = new JLabel("Category:");
        JLabel descriptionLabel = new JLabel("Description:");
        categoryField = new JTextField();
        descriptionField = new JTextField();
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");

        submitButton.addActionListener(this);
        backButton.addActionListener(this);

        setLayout(new GridLayout(4, 2, 10, 10));
        add(categoryLabel);
        add(categoryField);
        add(descriptionLabel);
        add(descriptionField);
        add(submitButton);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String category = categoryField.getText();
            String description = descriptionField.getText();

            if (category.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            ComplaintManager.raiseComplaint(studentUsername, category, description);
            JOptionPane.showMessageDialog(this, "Complaint submitted successfully!");
            new StudentDashboard(studentUsername).setVisible(true);
            dispose();
        } else if (e.getSource() == backButton) {
            new StudentDashboard(studentUsername).setVisible(true);
            dispose();
        }
    }
}
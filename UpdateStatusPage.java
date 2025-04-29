import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateStatusPage extends JFrame implements ActionListener {
    JTextField complaintIdField, statusField;
    JButton updateButton, backButton;

    UpdateStatusPage() {
        setTitle("Update Complaint Status");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel complaintIdLabel = new JLabel("Complaint ID:");
        JLabel statusLabel = new JLabel("New Status:");
        complaintIdField = new JTextField();
        statusField = new JTextField();
        updateButton = new JButton("Update");
        backButton = new JButton("Back");

        updateButton.addActionListener(this);
        backButton.addActionListener(this);

        setLayout(new GridLayout(4, 2, 10, 10));
        add(complaintIdLabel);
        add(complaintIdField);
        add(statusLabel);
        add(statusField);
        add(updateButton);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String complaintId = complaintIdField.getText();
            String status = statusField.getText();

            if (complaintId.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            boolean updated = ComplaintManager.updateComplaintStatus(complaintId, status);
            if (updated) {
                JOptionPane.showMessageDialog(this, "Status updated successfully!");
                new AdminDashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Complaint ID not found!");
            }
        } else if (e.getSource() == backButton) {
            new AdminDashboard().setVisible(true);
            dispose();
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame implements ActionListener {
    JButton viewComplaintsButton, updateStatusButton, addAdminButton, logoutButton;

    AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        viewComplaintsButton = new JButton("View All Complaints");
        updateStatusButton = new JButton("Update Complaint Status");
        addAdminButton = new JButton("Add New Admin");
        logoutButton = new JButton("Logout");

        viewComplaintsButton.addActionListener(this);
        updateStatusButton.addActionListener(this);
        addAdminButton.addActionListener(this);
        logoutButton.addActionListener(this);

        setLayout(new GridLayout(5, 1, 10, 10));
        add(new JLabel("Admin Actions", SwingConstants.CENTER));
        add(viewComplaintsButton);
        add(updateStatusButton);
        add(addAdminButton);
        add(logoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewComplaintsButton) {
            new ViewComplaintsPage().setVisible(true);
            dispose();
        } else if (e.getSource() == updateStatusButton) {
            new UpdateStatusPage().setVisible(true);
            dispose();
        } else if (e.getSource() == addAdminButton) {
            new AdminSignupPage().setVisible(true);
            dispose();
        } else if (e.getSource() == logoutButton) {
            new HomePage().setVisible(true);
            dispose();
        }
    }
}
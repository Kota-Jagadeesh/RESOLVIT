import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * UpdateStatusPage class provides a GUI interface for administrators to update the status
 * of complaints that have already been marked as "Resolved" by managers.
 * <p>
 * The administrator can enter the complaint ID and the new status to update it.
 * If the complaint ID is invalid or the complaint is not yet resolved by the manager,
 * the update will be rejected.
 * </p>
 * 
 * <p>
 * Usage example:
 * <pre>
 *     UpdateStatusPage page = new UpdateStatusPage();
 *     page.setVisible(true);
 * </pre>
 * </p>
 */
public class UpdateStatusPage extends JFrame implements ActionListener {

    /** Text field to input the complaint ID to update. */
    JTextField complaintIdField;

    /** Text field to input the new status for the complaint. */
    JTextField statusField;

    /** Button to trigger the status update action. */
    JButton updateButton;

    /** Button to return to the admin dashboard. */
    JButton backButton;

    /**
     * Constructs the UpdateStatusPage GUI window for administrators.
     * Sets up all UI components and their event listeners.
     */
    public UpdateStatusPage() {
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

    /**
     * Handles button click events.
     * <ul>
     *   <li>If the update button is clicked, validates input fields and attempts to update
     *       the complaint status by calling {@code ComplaintManager.updateComplaintStatus} with
     *       the role as "admin".</li>
     *   <li>If the back button is clicked, closes this window and opens the admin dashboard.</li>
     * </ul>
     * 
     * @param e The action event triggered by a button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String complaintId = complaintIdField.getText();
            String status = statusField.getText();

            if (complaintId.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Attempt to update complaint status as admin
            boolean updated = ComplaintManager.updateComplaintStatus(complaintId, status, "admin", "");
            if (updated) {
                JOptionPane.showMessageDialog(this, "Status updated successfully!");
                new AdminDashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Complaint ID or not yet Resolved by manager!");
            }
        } else if (e.getSource() == backButton) {
            new AdminDashboard().setVisible(true);
            dispose();
        }
    }
}

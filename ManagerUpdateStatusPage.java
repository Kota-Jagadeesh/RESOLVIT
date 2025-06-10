import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ManagerUpdateStatusPage class provides a GUI interface for category managers
 * to update the status of complaints assigned to them.
 * <p>
 * The page allows the manager to input a complaint ID and select a new status
 * from predefined options ("In Progress" or "Resolved"). Upon clicking "Update",
 * it attempts to update the status of the complaint via the ComplaintManager class.
 * The manager can also navigate back to the dashboard.
 * </p>
 * 
 * <p>
 * Usage example:
 * <pre>
 *     ManagerUpdateStatusPage page = new ManagerUpdateStatusPage("managerUsername");
 *     page.setVisible(true);
 * </pre>
 * </p>
 */
public class ManagerUpdateStatusPage extends JFrame implements ActionListener {
    
    /** Text field to input the complaint ID. */
    JTextField complaintIdField;
    
    /** Combo box to select the new status of the complaint. */
    JComboBox<String> statusField;
    
    /** Button to submit the status update request. */
    JButton updateButton;
    
    /** Button to go back to the manager dashboard. */
    JButton backButton;
    
    /** Username of the currently logged-in manager, used to validate complaint ownership. */
    String managerUsername;

    /**
     * Constructs the ManagerUpdateStatusPage GUI with the specified manager's username.
     * 
     * @param managerUsername The username of the logged-in category manager.
     */
    public ManagerUpdateStatusPage(String managerUsername) {
        this.managerUsername = managerUsername;
        
        setTitle("Update Complaint Status - Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel complaintIdLabel = new JLabel("Complaint ID:");
        JLabel statusLabel = new JLabel("New Status:");
        
        complaintIdField = new JTextField();
        
        String[] statuses = {"In Progress", "Resolved"};
        statusField = new JComboBox<>(statuses);
        
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
     * Handles action events from the update and back buttons.
     * <ul>
     *   <li>If "Update" is pressed, validates the complaint ID, then attempts to update
     *       the complaint status via {@code ComplaintManager.updateComplaintStatus}.</li>
     *   <li>If "Back" is pressed, closes this window and returns to the manager dashboard.</li>
     * </ul>
     * 
     * @param e The action event triggered by a button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String complaintId = complaintIdField.getText();
            String status = (String) statusField.getSelectedItem();

            if (complaintId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complaint ID is required!");
                return;
            }

            // Attempt to update complaint status, checking if complaint belongs to this manager
            boolean updated = ComplaintManager.updateComplaintStatus(complaintId, status, "manager", managerUsername);
            if (updated) {
                JOptionPane.showMessageDialog(this, "Status updated successfully!");
                new ManagerDashboard(managerUsername).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Complaint ID or not assigned to you!");
            }
        } else if (e.getSource() == backButton) {
            new ManagerDashboard(managerUsername).setVisible(true);
            dispose();
        }
    }
}

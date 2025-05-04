import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * UpdateStatusPage class represents the interface for administrators to update the status
 * of a complaint in the complaint management system. It provides a graphical user interface
 * with fields for entering the complaint ID and new status, and buttons for updating the
 * status or returning to the admin dashboard. The class extends JFrame for window creation
 * and implements ActionListener to handle button click events.
 */
public class UpdateStatusPage extends JFrame implements ActionListener {
    // Instance variables for UI components
    JTextField complaintIdField; // Text field for entering the complaint ID
    JTextField statusField;      // Text field for entering the new status
    JButton updateButton;        // Button to submit the status update
    JButton backButton;          // Button to return to the admin dashboard

    /**
     * Constructor for UpdateStatusPage.
     * Initializes the status update window, configures its properties, creates UI components
     * (labels, text fields, and buttons), assigns action listeners, and arranges components
     * in a grid layout.
     */
    UpdateStatusPage() {
        // Set the title of the window
        setTitle("Update Complaint Status");

        // Set the size of the window (width: 400px, height: 300px)
        setSize(400, 300);

        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Initialize UI components
        JLabel complaintIdLabel = new JLabel("Complaint ID:"); // Label for complaint ID field
        JLabel statusLabel = new JLabel("New Status:");        // Label for status field
        complaintIdField = new JTextField();                   // Text field for complaint ID input
        statusField = new JTextField();                        // Text field for new status input
        updateButton = new JButton("Update");                  // Button to initiate status update
        backButton = new JButton("Back");                      // Button to navigate back to dashboard

        // Register the current object as the action listener for buttons
        updateButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set the layout to a GridLayout with 4 rows, 2 columns, and 10px gaps
        setLayout(new GridLayout(4, 2, 10, 10));

        // Add components to the layout in the specified order
        add(complaintIdLabel);
        add(complaintIdField);
        add(statusLabel);
        add(statusField);
        add(updateButton);
        add(backButton);
    }

    /**
     * Handles action events triggered by button clicks.
     * For the update button, it validates the input fields, updates the complaint status
     * using ComplaintManager, and navigates to the admin dashboard if successful. For the
     * back button, it navigates to the admin dashboard without updating. Displays appropriate
     * messages for validation errors, successful updates, or invalid complaint IDs.
     *
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            // Retrieve entered complaint ID and new status
            String complaintId = complaintIdField.getText();
            String status = statusField.getText();

            // Validate that both fields are non-empty
            if (complaintId.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Attempt to update the complaint status using ComplaintManager
            boolean updated = ComplaintManager.updateComplaintStatus(complaintId, status);
            if (updated) {
                // Display success message
                JOptionPane.showMessageDialog(this, "Status updated successfully!");
                // Open the Admin Dashboard and close the current window
                new AdminDashboard().setVisible(true);
                dispose();
            } else {
                // Display error message if the complaint ID is not found
                JOptionPane.showMessageDialog(this, "Complaint ID not found!");
            }
        } else if (e.getSource() == backButton) {
            // Open the Admin Dashboard and close the current window
            new AdminDashboard().setVisible(true);
            dispose();
        }
    }
}
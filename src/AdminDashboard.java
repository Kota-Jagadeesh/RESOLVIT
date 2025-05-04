import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * AdminDashboard class represents the main dashboard for administrators in a complaint management system.
 * It provides a graphical user interface with buttons to view complaints, update complaint status,
 */
public class AdminDashboard extends JFrame implements ActionListener {
    // Instance variables for buttons
    private JButton viewComplaintsButton; // Button to view all complaints
    private JButton updateStatusButton;   // Button to update complaint status
    private JButton addAdminButton;       // Button to add a new admin
    private JButton logoutButton;         // Button to log-out

    /**
     * Constructor for AdminDashboard.
     * Initializes the admin dashboard window, sets its properties, creates buttons,
     */
    public AdminDashboard() {
        // Set the title of the window
        setTitle("Admin Dashboard");
        // Set the size of the window (width: 400px, height: 300px)
        setSize(400, 300);
        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Initialize buttons with descriptive labels
        viewComplaintsButton = new JButton("View All Complaints");
        updateStatusButton = new JButton("Update Complaint Status");
        addAdminButton = new JButton("Add New Admin");
        logoutButton = new JButton("Logout");

        // Registers the current object as the action listener for all buttons
        viewComplaintsButton.addActionListener(this);
        updateStatusButton.addActionListener(this);
        addAdminButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // Set the layout to a GridLayout with 5 rows, 1 column, and 10px gaps
        setLayout(new GridLayout(5, 1, 10, 10));

        // Add a centered label as a header for the dashboard
        add(new JLabel("Admin Actions", SwingConstants.CENTER));

        // Add buttons to the layout
        add(viewComplaintsButton);
        add(updateStatusButton);
        add(addAdminButton);
        add(logoutButton);
    }
    /**
     * Handles action events triggered by button clicks.
     * Depending on the button clicked, it opens the corresponding page (View Complaints,
     * Update Status, Add Admin, or Home) and closes the current dashboard window.
     *@param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which button was clicked
        if (e.getSource() == viewComplaintsButton) {
            // Open the View Complaints page and close the dashboard
            new ViewComplaintsPage().setVisible(true);
            dispose();
        } else if (e.getSource() == updateStatusButton) {
            // Open the Update Status page and close the dashboard
            new UpdateStatusPage().setVisible(true);
            dispose();
        } else if (e.getSource() == addAdminButton) {
            // Open the Admin Signup page and close the dashboard
            new AdminSignupPage().setVisible(true);
            dispose();
        } else if (e.getSource() == logoutButton) {
            // Open the Home page and close the dashboard
            new HomePage().setVisible(true);
            dispose();
        }
    }
    /**
     * Main method to launch the AdminDashboard.
     * Creates an instance of AdminDashboard and makes it visible.
     *@param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the dashboard creation on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            AdminDashboard dashboard = new AdminDashboard();
            dashboard.setVisible(true);
        });
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * AdminDashboard class represents the main dashboard for administrators.
 * It allows viewing complaints, forwarding complaints to managers, and logging out.
 */
public class AdminDashboard extends JFrame implements ActionListener {

    // Buttons for admin actions
    private final JButton viewComplaintsButton;
    private final JButton forwardComplaintButton;
    private final JButton logoutButton;

    /**
     * Constructor to initialize the AdminDashboard window.
     * Sets up the GUI components and layout.
     */
    public AdminDashboard() {
        // Set the title of the frame
        setTitle("Admin Dashboard");

        // Set the size of the frame
        setSize(400, 300);

        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Initialize buttons
        viewComplaintsButton = new JButton("View All Complaints");
        forwardComplaintButton = new JButton("Forward Complaint");
        logoutButton = new JButton("Logout");

        // Register event listeners for buttons
        viewComplaintsButton.addActionListener(this);
        forwardComplaintButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // Set layout with 4 rows and 1 column, and spacing between components
        setLayout(new GridLayout(4, 1, 10, 10));

        // Add components to the frame
        add(new JLabel("Admin Actions", SwingConstants.CENTER)); // Heading label
        add(viewComplaintsButton);   // View complaints button
        add(forwardComplaintButton); // Forward complaint button
        add(logoutButton);           // Logout button
    }

    /**
     * Handles button click events.
     * Navigates to different pages based on the action performed.
     *
     * @param e the ActionEvent triggered by user interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewComplaintsButton) {
            // Opens the ViewComplaintsPage
            new ViewComplaintsPage().setVisible(true);
            dispose(); // Closes the current AdminDashboard window
        } else if (e.getSource() == forwardComplaintButton) {
            // Opens the ForwardComplaintPage
            new ForwardComplaintPage().setVisible(true);
            dispose(); // Closes the current AdminDashboard window
        } else if (e.getSource() == logoutButton) {
            // Redirects to the HomePage
            new HomePage().setVisible(true);
            dispose(); // Closes the current AdminDashboard window
        }
    }
}

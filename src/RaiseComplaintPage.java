import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * RaiseComplaintPage class represents the interface for students to raise a new complaint
 * in the complaint management system. It provides a graphical user interface with fields
 * for entering the complaint category and description, and buttons for submitting the
 * complaint or returning to the student dashboard. The class extends JFrame for window
 * creation and implements ActionListener to handle button click events.
 */
public class RaiseComplaintPage extends JFrame implements ActionListener {
    // Instance variables for UI components and student identifier
    JTextField categoryField;     // Text field for entering the complaint category
    JTextField descriptionField;  // Text field for entering the complaint description
    JButton submitButton;         // Button to submit the complaint
    JButton backButton;           // Button to return to the student dashboard
    String studentUsername;       // Username of the student raising the complaint

    /**
     * Constructor for RaiseComplaintPage.
     * Initializes the complaint submission window, configures its properties, creates
     * UI components (labels, text fields, and buttons), assigns action listeners, and
     * arranges components in a grid layout. Stores the student's username for complaint
     * submission.
     * @param studentUsername The username of the student raising the complaint
     */
    RaiseComplaintPage(String studentUsername) {
        // Store the student username
        this.studentUsername = studentUsername;

        // Set the title of the window
        setTitle("Raise Complaint");

        // Set the size of the window (width: 400px, height: 300px)
        setSize(400, 300);

        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Initialize UI components
        JLabel categoryLabel = new JLabel("Category:");    // Label for category field
        JLabel descriptionLabel = new JLabel("Description:"); // Label for description field
        categoryField = new JTextField();                  // Text field for category input
        descriptionField = new JTextField();               // Text field for description input
        submitButton = new JButton("Submit");              // Button to submit the complaint
        backButton = new JButton("Back");                  // Button to navigate back to dashboard

        // Register the current object as the action listener for buttons
        submitButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set the layout to a GridLayout with 4 rows, 2 columns, and 10px gaps
        setLayout(new GridLayout(4, 2, 10, 10));

        // Add components to the layout in the specified order
        add(categoryLabel);
        add(categoryField);
        add(descriptionLabel);
        add(descriptionField);
        add(submitButton);
        add(backButton);
    }

    /**
     * Handles action events triggered by button clicks.
     * For the submit button, it validates the input fields, raises a new complaint
     * using ComplaintManager, and navigates to the student dashboard. For the back
     * button, it navigates to the student dashboard without submitting a complaint.
     * Displays appropriate messages for validation errors or successful submission.
     *
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Retrieve entered category and description
            String category = categoryField.getText();
            String description = descriptionField.getText();

            // Validate that both fields are non-empty
            if (category.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Raise a new complaint using the ComplaintManager
            ComplaintManager.raiseComplaint(studentUsername, category, description);
            // Display success message
            JOptionPane.showMessageDialog(this, "Complaint submitted successfully!");
            // Open the Student Dashboard and close the current window
            new StudentDashboard(studentUsername).setVisible(true);
            dispose();
        } else if (e.getSource() == backButton) {
            // Open the Student Dashboard and close the current window
            new StudentDashboard(studentUsername).setVisible(true);
            dispose();
        }
    }
}
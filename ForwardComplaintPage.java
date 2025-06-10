import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The {@code ForwardComplaintPage} class represents the GUI window
 * used by administrators to forward a student complaint to the appropriate
 * category manager (Hostel, Food, or College).
 *
 * <p>This class allows the admin to:
 * <ul>
 *   <li>Enter a complaint ID</li>
 *   <li>Select the category the complaint belongs to</li>
 *   <li>Forward the complaint to the assigned manager</li>
 *   <li>Navigate back to the admin dashboard</li>
 * </ul>
 *
 * <p>It interacts with the {@code ComplaintManager} class to perform the
 * forwarding operation by updating the complaint's status and assigned manager
 * in the underlying CSV file.
 */
public class ForwardComplaintPage extends JFrame implements ActionListener {

    /** Input field for the complaint ID to be forwarded. */
    JTextField complaintIdField;

    /** Dropdown list to select the complaint category. */
    JComboBox<String> categoryField;

    /** Button to forward the complaint to a manager. */
    JButton forwardButton;

    /** Button to return to the AdminDashboard. */
    JButton backButton;

    /**
     * Constructs a new ForwardComplaintPage GUI.
     * Initializes and places all UI components including:
     * complaint ID input, category selection, and control buttons.
     */
    public ForwardComplaintPage() {
        setTitle("Forward Complaint");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI Labels
        JLabel complaintIdLabel = new JLabel("Complaint ID:");
        JLabel categoryLabel = new JLabel("Category:");

        // Input fields
        complaintIdField = new JTextField();
        String[] categories = {"Hostel", "Food", "College"};
        categoryField = new JComboBox<>(categories);

        // Buttons
        forwardButton = new JButton("Forward");
        backButton = new JButton("Back");

        // Register action listeners
        forwardButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set layout and add components
        setLayout(new GridLayout(4, 2, 10, 10));
        add(complaintIdLabel);
        add(complaintIdField);
        add(categoryLabel);
        add(categoryField);
        add(forwardButton);
        add(backButton);
    }

    /**
     * Handles button click events for the forward and back buttons.
     * 
     * @param e The ActionEvent triggered by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == forwardButton) {
            String complaintId = complaintIdField.getText().trim();
            String category = (String) categoryField.getSelectedItem();

            if (complaintId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complaint ID is required!");
                return;
            }

            boolean forwarded = ComplaintManager.forwardComplaint(complaintId, category);
            if (forwarded) {
                JOptionPane.showMessageDialog(this, "Complaint forwarded successfully!");
                new AdminDashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Complaint ID or category mismatch!");
            }
        } else if (e.getSource() == backButton) {
            new AdminDashboard().setVisible(true);
            dispose();
        }
    }
}

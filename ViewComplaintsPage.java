import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
/**
 * ViewComplaintsPage class represents the interface for administrators to view all complaints
 * in the complaint management system. It provides a graphical user interface with an HTML-formatted
 * text pane to display complaints and a button to return to the admin dashboard. The class extends
 * JFrame for window creation and implements ActionListener to handle button click events.
 */
public class ViewComplaintsPage extends JFrame implements ActionListener {
    // Instance variables for UI components
    JTextPane complaintsPane; // Text pane to display complaints in HTML format
    JButton backButton;       // Button to return to the admin dashboard

    /**
     * Constructor for ViewComplaintsPage.
     * Initializes the complaint viewing window, configures its properties, creates UI components
     * (text pane and button), assigns action listeners, and arranges components using a BorderLayout.
     * Loads all complaints upon initialization.
     */
    ViewComplaintsPage() {
        // Set the title of the window
        setTitle("View All Complaints");

        // Set the size of the window (width: 600px, height: 500px)
        setSize(600, 500);

        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Initialize the text pane for displaying complaints
        complaintsPane = new JTextPane();
        complaintsPane.setEditable(false);             // Make the text pane read-only
        complaintsPane.setContentType("text/html");    // Set content type to HTML for formatting

        // Initialize the back button
        backButton = new JButton("Back");              // Button to navigate back to dashboard

        // Register the current object as the action listener for the back button
        backButton.addActionListener(this);

        // Set the layout to BorderLayout
        setLayout(new BorderLayout());

        // Add the text pane inside a scroll pane to the center of the layout
        add(new JScrollPane(complaintsPane), BorderLayout.CENTER);

        // Create a panel for the back button and add it to the south of the layout
        JPanel southPanel = new JPanel();
        southPanel.add(backButton);
        add(southPanel, BorderLayout.SOUTH);

        // Load and display all complaints
        loadComplaints();
    }

    /**
     * Loads all complaints from the ComplaintManager and displays them in the text pane.
     * Formats the complaints as an HTML list, including complaint ID, student ID, category,
     * description, and status. Displays a message if no complaints are found.
     */
    private void loadComplaints() {
        // Retrieve all complaints using ComplaintManager
        List<Complaint> complaints = ComplaintManager.viewAllComplaints();
        // Initialize a StringBuilder to construct HTML content
        StringBuilder html = new StringBuilder();
        // Start HTML content with a heading
        html.append("<html><body><h2>All Complaints</h2><ul>");
        // Iterate through complaints and append each as an HTML list item
        for (Complaint complaint : complaints) {
            html.append("<li><b>").append(complaint.getComplaintId()).append("</b><br>")
                .append("Student ID: ").append(complaint.getStudentId()).append("<br>")
                .append("Category: ").append(complaint.getCategory()).append("<br>")
                .append("Description: ").append(complaint.getDescription()).append("<br>")
                .append("Status: ").append(complaint.getStatus()).append("</li><br>");
        }
        // Display a message if no complaints are found
        if (complaints.isEmpty()) {
            html.append("<p>No complaints found!</p>");
        }
        // Close HTML content
        html.append("</ul></body></html>");
        // Set the HTML content in the text pane
        complaintsPane.setText(html.toString());
    }

    /**
     * Handles action events triggered by button clicks.
     * For the back button, it navigates to the admin dashboard and closes the current window.
     *
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Open the Admin Dashboard and close the current window
            new AdminDashboard().setVisible(true);
            dispose();
        }
    }
}
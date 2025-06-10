import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * StudentDashboard class represents the dashboard interface for students in the complaint management system.
 * It provides a graphical user interface to view complaints, raise new complaints, and log out. The dashboard
 * displays the student's complaints in an HTML-formatted text pane. The class extends JFrame for window creation
 * and implements ActionListener to handle button click events.
 */
public class StudentDashboard extends JFrame implements ActionListener {
    // Instance variables for UI components and student identifier
    JTextPane complaintsPane;         // Text pane to display complaints in HTML format
    JButton raiseComplaintButton;     // Button to navigate to the complaint submission page
    JButton viewComplaintsButton;     // Button to refresh and display the student's complaints
    JButton logoutButton;             // Button to log out and return to the home page
    String studentUsername;           // Username of the logged-in student

    /**
     * Constructor for StudentDashboard.
     * Initializes the student dashboard window, configures its properties, creates UI components
     * (text pane, buttons, and panel), assigns action listeners, and arranges components using
     * a BorderLayout. Loads the student's complaints upon initialization.
     *
     * @param username The username of the logged-in student
     */
    StudentDashboard(String username) {
        // Store the student username
        this.studentUsername = username;

        // Set the title of the window, including the username
        setTitle("Student Dashboard - " + username);

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

        // Initialize buttons
        raiseComplaintButton = new JButton("Raise Complaint"); // Button for raising a new complaint
        viewComplaintsButton = new JButton("View My Complaints"); // Button for refreshing complaint list
        logoutButton = new JButton("Logout");                 // Button for logging out

        // Register the current object as the action listener for buttons
        raiseComplaintButton.addActionListener(this);
        viewComplaintsButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // Set the layout to BorderLayout
        setLayout(new BorderLayout());

        // Add the text pane inside a scroll pane to the center of the layout
        add(new JScrollPane(complaintsPane), BorderLayout.CENTER);

        // Create a panel for buttons and add it to the south of the layout
        JPanel southPanel = new JPanel();
        southPanel.add(raiseComplaintButton);
        southPanel.add(viewComplaintsButton);
        southPanel.add(logoutButton);
        add(southPanel, BorderLayout.SOUTH);

        // Load and display the student's complaints
        loadComplaints();
    }
    /**
     * Loads the student's complaints from the ComplaintManager and displays them in the text pane.
     * Formats the complaints as an HTML list, including complaint ID, category, description, and status.
     * Displays a message if no complaints are found.
     */
    private void loadComplaints() {
        // Retrieve the student's complaints using ComplaintManager
        List<Complaint> complaints = ComplaintManager.viewStudentComplaints(studentUsername);
        // Initialize a StringBuilder to construct HTML content
        StringBuilder html = new StringBuilder();
        // Start HTML content with a heading
        html.append("<html><body><h2>Your Complaints</h2><ul>");
        // Iterate through complaints and append each as an HTML list item
        for (Complaint complaint : complaints) {
            html.append("<li><b>").append(complaint.getComplaintId()).append("</b><br>")
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
     * Navigates to the complaint submission page, refreshes the complaint list, or logs out
     * based on the button clicked, closing the current window as needed.
     *
     * @param e The ActionEvent object containing details about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == raiseComplaintButton) {
            // Open the Raise Complaint page and close the current window
            new RaiseComplaintPage(studentUsername).setVisible(true);
            dispose();
        } else if (e.getSource() == viewComplaintsButton) {
            // Refresh and display the student's complaints
            loadComplaints();
        } else if (e.getSource() == logoutButton) {
            // Open the Home page and close the current window
            new HomePage().setVisible(true);
            dispose();
        }
    }
}
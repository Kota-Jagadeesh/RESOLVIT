import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * ViewComplaintsPage class provides a graphical user interface for administrators
 * to view a list of all complaints in the system.
 * <p>
 * The complaints are displayed in an HTML formatted JTextPane with details such as
 * complaint ID, student ID, category, description, status, and assigned manager.
 * </p>
 * <p>
 * The interface includes a "Back" button to return to the admin dashboard.
 * </p>
 * 
 * <p>
 * Usage example:
 * <pre>
 *     ViewComplaintsPage viewPage = new ViewComplaintsPage();
 *     viewPage.setVisible(true);
 * </pre>
 * </p>
 */
public class ViewComplaintsPage extends JFrame implements ActionListener {

    /** Text pane to display the list of complaints in HTML format. */
    JTextPane complaintsPane;

    /** Button to navigate back to the admin dashboard. */
    JButton backButton;

    /**
     * Constructs the ViewComplaintsPage window.
     * Sets up the UI components, loads complaint data, and registers event listeners.
     */
    public ViewComplaintsPage() {
        setTitle("View All Complaints");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        complaintsPane = new JTextPane();
        complaintsPane.setEditable(false);
        complaintsPane.setContentType("text/html");

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(new JScrollPane(complaintsPane), BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.add(backButton);
        add(southPanel, BorderLayout.SOUTH);

        loadComplaints();
    }

    /**
     * Loads all complaints from the ComplaintManager and formats them as HTML.
     * The formatted HTML content is set to the JTextPane for display.
     * If no complaints are found, a message is shown instead.
     */
    private void loadComplaints() {
        List<Complaint> complaints = ComplaintManager.viewAllComplaints();
        StringBuilder html = new StringBuilder();
        html.append("<h2>All Complaints</h2><ul>");
        for (Complaint complaint : complaints) {
            html.append("<li><b>")
                .append(complaint.getComplaintId())
                .append("</b><br>")
                .append("Student ID: ").append(complaint.getStudentId()).append("<br>")
                .append("Category: ").append(complaint.getCategory()).append("<br>")
                .append("Description: ").append(complaint.getDescription()).append("<br>")
                .append("Status: ").append(complaint.getStatus()).append("<br>")
                .append("Assigned Manager: ").append(complaint.getAssignedManager().isEmpty() ? "None" : complaint.getAssignedManager())
                .append("</li><br>");
        }
        if (complaints.isEmpty()) {
            html.append("<li>No complaints found!</li>");
        }
        html.append("</ul>");
        complaintsPane.setText(html.toString());
    }

    /**
     * Handles action events for the back button.
     * When the back button is clicked, the admin dashboard is opened and this window is closed.
     * 
     * @param e The action event triggered by the button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new AdminDashboard().setVisible(true);
            dispose();
        }
    }
}

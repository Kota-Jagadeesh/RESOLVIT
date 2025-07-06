import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

/**
 * The {@code ManagerDashboard} class represents the main interface for category managers.
 * It allows them to view and manage complaints that have been forwarded to their category.
 * 
 * <p>This dashboard provides:
 * <ul>
 *   <li>A view of all complaints assigned to the manager</li>
 *   <li>Navigation to update complaint statuses</li>
 *   <li>Logout functionality to return to the home page</li>
 * </ul>
 * 
 * <p>Complaints are dynamically retrieved and displayed using HTML formatting in a JTextPane.
 */
public class ManagerDashboard extends JFrame implements ActionListener {

    /** Text pane to display complaints in HTML format */
    JTextPane complaintsPane;

    /** Button to view assigned complaints */
    JButton viewComplaintsButton;

    /** Button to navigate to update complaint status */
    JButton updateStatusButton;

    /** Button to logout and return to HomePage */
    JButton logoutButton;

    /** Username of the currently logged-in manager */
    String managerUsername;

    /**
     * Constructs the ManagerDashboard window for a given manager.
     *
     * @param username The username of the logged-in manager.
     */
    public ManagerDashboard(String username) {
        this.managerUsername = username;
        setTitle("Manager Dashboard - " + username);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        complaintsPane = new JTextPane();
        complaintsPane.setEditable(false);
        complaintsPane.setContentType("text/html");

        viewComplaintsButton = new JButton("View My Complaints");
        updateStatusButton = new JButton("Update Complaint Status");
        logoutButton = new JButton("Logout");

        viewComplaintsButton.addActionListener(this);
        updateStatusButton.addActionListener(this);
        logoutButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(new JScrollPane(complaintsPane), BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(viewComplaintsButton);
        southPanel.add(updateStatusButton);
        southPanel.add(logoutButton);
        add(southPanel, BorderLayout.SOUTH);

        loadComplaints();
    }

    /**
     * Loads and displays complaints assigned to the current manager.
     * Complaints are retrieved using {@code ComplaintManager.viewManagerComplaints()}.
     * The output is displayed using HTML in a JTextPane.
     */
    private void loadComplaints() {
        List<Complaint> complaints = ComplaintManager.viewManagerComplaints(managerUsername);
        StringBuilder html = new StringBuilder();
        html.append("<h2>My Complaints</h2><ul>");
        for (Complaint complaint : complaints) {
            html.append("<li><b>")
                .append(complaint.getComplaintId())
                .append("</b><br>")
                .append("Student ID: ").append(complaint.getStudentId()).append("<br>")
                .append("Category: ").append(complaint.getCategory()).append("<br>")
                .append("Description: ").append(complaint.getDescription()).append("<br>")
                .append("Status: ").append(complaint.getStatus()).append("</li><br>");
        }
        if (complaints.isEmpty()) {
            html.append("<li>No complaints assigned!</li>");
        }
        html.append("</ul>");
        complaintsPane.setText(html.toString());
    }

    /**
     * Handles button click events for viewing complaints, updating status, and logging out.
     *
     * @param e The ActionEvent triggered by button clicks.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewComplaintsButton) {
            loadComplaints();
        } else if (e.getSource() == updateStatusButton) {
            new ManagerUpdateStatusPage(managerUsername).setVisible(true);
            dispose();
        } else if (e.getSource() == logoutButton) {
            new HomePage().setVisible(true);
            dispose();
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewComplaintsPage extends JFrame implements ActionListener {
    JTextPane complaintsPane;
    JButton backButton;

    ViewComplaintsPage() {
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

    private void loadComplaints() {
        List<Complaint> complaints = ComplaintManager.viewAllComplaints();
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h2>All Complaints</h2><ul>");
        for (Complaint complaint : complaints) {
            html.append("<li><b>").append(complaint.getComplaintId()).append("</b><br>")
                .append("Student ID: ").append(complaint.getStudentId()).append("<br>")
                .append("Category: ").append(complaint.getCategory()).append("<br>")
                .append("Description: ").append(complaint.getDescription()).append("<br>")
                .append("Status: ").append(complaint.getStatus()).append("</li><br>");
        }
        if (complaints.isEmpty()) {
            html.append("<p>No complaints found!</p>");
        }
        html.append("</ul></body></html>");
        complaintsPane.setText(html.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new AdminDashboard().setVisible(true);
            dispose();
        }
    }
}
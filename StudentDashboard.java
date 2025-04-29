import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class StudentDashboard extends JFrame implements ActionListener {
    JTextPane complaintsPane;
    JButton raiseComplaintButton, viewComplaintsButton, logoutButton;
    String studentUsername;

    StudentDashboard(String username) {
        this.studentUsername = username;
        setTitle("Student Dashboard - " + username);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        complaintsPane = new JTextPane();
        complaintsPane.setEditable(false);
        complaintsPane.setContentType("text/html");

        raiseComplaintButton = new JButton("Raise Complaint");
        viewComplaintsButton = new JButton("View My Complaints");
        logoutButton = new JButton("Logout");

        raiseComplaintButton.addActionListener(this);
        viewComplaintsButton.addActionListener(this);
        logoutButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(new JScrollPane(complaintsPane), BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(raiseComplaintButton);
        southPanel.add(viewComplaintsButton);
        southPanel.add(logoutButton);
        add(southPanel, BorderLayout.SOUTH);

        loadComplaints();
    }

    private void loadComplaints() {
        List<Complaint> complaints = ComplaintManager.viewStudentComplaints(studentUsername);
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h2>Your Complaints</h2><ul>");
        for (Complaint complaint : complaints) {
            html.append("<li><b>").append(complaint.getComplaintId()).append("</b><br>")
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
        if (e.getSource() == raiseComplaintButton) {
            new RaiseComplaintPage(studentUsername).setVisible(true);
            dispose();
        } else if (e.getSource() == viewComplaintsButton) {
            loadComplaints();
        } else if (e.getSource() == logoutButton) {
            new HomePage().setVisible(true);
            dispose();
        }
    }
}
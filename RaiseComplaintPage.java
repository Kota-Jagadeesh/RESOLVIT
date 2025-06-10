import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * RaiseComplaintPage class provides a GUI interface for students to raise new complaints.
 * <p>
 * The complaint category is limited to predefined options: "Hostel", "Food", or "College"
 * selectable from a dropdown menu. Students can enter a description of their complaint
 * and submit it. The page also allows returning back to the student dashboard.
 * </p>
 * 
 * <p>
 * Usage example:
 * <pre>
 *     RaiseComplaintPage page = new RaiseComplaintPage("studentUsername");
 *     page.setVisible(true);
 * </pre>
 * </p>
 */
public class RaiseComplaintPage extends JFrame implements ActionListener {
    
    /** Dropdown combo box for selecting complaint category. */
    JComboBox<String> categoryField;
    
    /** Text field to enter complaint description. */
    JTextField descriptionField;
    
    /** Button to submit the complaint. */
    JButton submitButton;
    
    /** Button to go back to the student dashboard. */
    JButton backButton;
    
    /** Username of the logged-in student raising the complaint. */
    String studentUsername;

    /**
     * Constructs the RaiseComplaintPage GUI for the specified student username.
     * 
     * @param studentUsername The username of the logged-in student.
     */
    public RaiseComplaintPage(String studentUsername) {
        this.studentUsername = studentUsername;
        
        setTitle("Raise Complaint");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel categoryLabel = new JLabel("Category:");
        JLabel descriptionLabel = new JLabel("Description:");
        
        String[] categories = {"Hostel", "Food", "College"};
        categoryField = new JComboBox<>(categories);
        
        descriptionField = new JTextField();
        
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");

        submitButton.addActionListener(this);
        backButton.addActionListener(this);

        setLayout(new GridLayout(4, 2, 10, 10));
        add(categoryLabel);
        add(categoryField);
        add(descriptionLabel);
        add(descriptionField);
        add(submitButton);
        add(backButton);
    }

    /**
     * Handles action events triggered by the submit and back buttons.
     * <ul>
     *   <li>If "Submit" is pressed, validates the description field, then raises
     *       a complaint using {@code ComplaintManager.raiseComplaint}.</li>
     *   <li>If "Back" is pressed, closes this window and returns to the student dashboard.</li>
     * </ul>
     * 
     * @param e The action event triggered by a button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String category = (String) categoryField.getSelectedItem();
            String description = descriptionField.getText();

            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Description is required!");
                return;
            }

            // Raise complaint with student username, selected category, and description
            ComplaintManager.raiseComplaint(studentUsername, category, description);
            JOptionPane.showMessageDialog(this, "Complaint submitted successfully!");
            new StudentDashboard(studentUsername).setVisible(true);
            dispose();
        } else if (e.getSource() == backButton) {
            new StudentDashboard(studentUsername).setVisible(true);
            dispose();
        }
    }
}

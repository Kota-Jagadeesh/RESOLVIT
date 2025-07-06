import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ComplaintManager class provides utility methods for managing complaints in a complaint management system.
 * It handles loading, saving, raising, viewing, forwarding, and updating complaints stored in a CSV file.
 */
public class ComplaintManager {
    private static final String FILE_PATH = "complaints.csv";
    private static final String MANAGERS_FILE = "managers.csv";
    private static int counter = 1000;

    /**
     * Loads all complaints from the complaints.csv file.
     */
    public static List<Complaint> loadComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", 6);
                if (data.length >= 5) {
                    String assignedManager = data.length > 5 ? data[5] : "";
                    complaints.add(new Complaint(data[0], data[1], data[2], data[3], data[4], assignedManager));
                    int num = Integer.parseInt(data[0].replace("CMP", ""));
                    if (num > counter) counter = num;
                }
            }
        } catch (IOException e) {
            // Silently handle file reading errors
        }
        return complaints;
    }

    /**
     * Saves a list of complaints to the complaints.csv file.
     */
    public static void saveComplaints(List<Complaint> complaints) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Complaint complaint : complaints) {
                writer.write(complaint.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Raises a new complaint with a fixed category (Hostel, Food, or College).
     */
    public static Complaint raiseComplaint(String studentId, String category, String description) {
        // Validate category
        if (!category.equals("Hostel") && !category.equals("Food") && !category.equals("College")) {
            throw new IllegalArgumentException("Invalid category. Must be Hostel, Food, or College.");
        }
        String complaintId = "CMP" + (++counter);
        Complaint complaint = new Complaint(complaintId, studentId, category, description, "Pending", "");
        List<Complaint> complaints = loadComplaints();
        complaints.add(complaint);
        saveComplaints(complaints);
        return complaint;
    }

    /**
     * Retrieves all complaints for a specific student.
     */
    public static List<Complaint> viewStudentComplaints(String studentId) {
        List<Complaint> complaints = loadComplaints();
        List<Complaint> studentComplaints = new ArrayList<>();
        for (Complaint complaint : complaints) {
            if (complaint.getStudentId().equals(studentId)) {
                studentComplaints.add(complaint);
            }
        }
        return studentComplaints;
    }

    /**
     * Retrieves all complaints in the system.
     */
    public static List<Complaint> viewAllComplaints() {
        return loadComplaints();
    }

    /**
     * Forwards a complaint to the appropriate category manager.
     */
    public static boolean forwardComplaint(String complaintId, String category) {
        List<Complaint> complaints = loadComplaints();
        String managerUsername = getManagerForCategory(category);
        if (managerUsername == null) {
            return false;
        }
        for (Complaint complaint : complaints) {
            if (complaint.getComplaintId().equals(complaintId) && complaint.getCategory().equals(category)) {
                complaint.setAssignedManager(managerUsername);
                complaint.setStatus("Forwarded");
                saveComplaints(complaints);
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves complaints assigned to a specific manager.
     */
    public static List<Complaint> viewManagerComplaints(String managerUsername) {
        List<Complaint> complaints = loadComplaints();
        List<Complaint> managerComplaints = new ArrayList<>();
        for (Complaint complaint : complaints) {
            if (managerUsername.equals(complaint.getAssignedManager())) {
                managerComplaints.add(complaint);
            }
        }
        return managerComplaints;
    }

    /**
     * Updates the status of a complaint by a manager or admin.
     */
    public static boolean updateComplaintStatus(String complaintId, String status, String userRole, String username) {
        List<Complaint> complaints = loadComplaints();
        for (Complaint complaint : complaints) {
            if (complaint.getComplaintId().equals(complaintId)) {
                if (userRole.equals("manager") && complaint.getAssignedManager().equals(username)) {
                    // Managers can update to "In Progress" or "Resolved"
                    if (status.equals("In Progress") || status.equals("Resolved")) {
                        complaint.setStatus(status);
                        saveComplaints(complaints);
                        return true;
                    }
                } else if (userRole.equals("admin") && complaint.getStatus().equals("Resolved")) {
                    // Admins can only update status of "Resolved" complaints
                    complaint.setStatus(status);
                    saveComplaints(complaints);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * Retrieves the manager username for a given category.
     */
    private static String getManagerForCategory(String category) {
        try (BufferedReader reader = new BufferedReader(new FileReader(MANAGERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3 && data[2].equals(category)) {
                    return data[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintManager {
    private static final String FILE_PATH = "complaints.csv";
    private static int counter = 1000;

    public static List<Complaint> loadComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    complaints.add(new Complaint(data[0], data[1], data[2], data[3], data[4]));
                    int num = Integer.parseInt(data[0].replace("CMP", ""));
                    if (num > counter) counter = num;
                }
            }
        } catch (IOException e) {
        }
        return complaints;
    }

    public static void saveComplaints(List<Complaint> complaints) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Complaint complaint : complaints) {
                writer.write(complaint.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Raise a new complaint
    public static Complaint raiseComplaint(String studentId, String category, String description) {
        String complaintId = "CMP" + (++counter);
        Complaint complaint = new Complaint(complaintId, studentId, category, description, "Pending");
        List<Complaint> complaints = loadComplaints();
        complaints.add(complaint);
        saveComplaints(complaints);
        return complaint;
    }

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

    // Get all complaints
    public static List<Complaint> viewAllComplaints() {
        return loadComplaints();
    }

    // Update complaint status
    public static boolean updateComplaintStatus(String complaintId, String status) {
        List<Complaint> complaints = loadComplaints();
        for (Complaint complaint : complaints) {
            if (complaint.getComplaintId().equals(complaintId)) {
                complaint.setStatus(status);
                saveComplaints(complaints);
                return true;
            }
        }
        return false;
    }
}
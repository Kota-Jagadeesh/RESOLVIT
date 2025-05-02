import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ComplaintManager class provides utility methods for managing complaints in a complaint management system.
 * It handles loading, saving, raising, viewing, and updating complaints stored in a CSV file.
 * The class uses a static counter to generate unique complaint IDs and operates on a list of Complaint objects.
 */
public class ComplaintManager {
    // Constants and static variables
    private static final String FILE_PATH = "complaints.csv"; // Path to the CSV file storing complaints
    private static int counter = 1000;                        // Counter for generating unique complaint IDs

    /**
     * Loads all complaints from the complaints.csv file into a list of Complaint objects.
     * Updates the counter based on the highest complaint ID found in the file.
     *
     * @return A List of Complaint objects loaded from the CSV file
     */
    public static List<Complaint> loadComplaints() {
        // Initialize an empty list to store complaints
        List<Complaint> complaints = new ArrayList<>();
        // Read the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                // Ensure the line has at least 5 fields before creating a Complaint object
                if (data.length >= 5) {
                    // Create and add a new Complaint object to the list
                    complaints.add(new Complaint(data[0], data[1], data[2], data[3], data[4]));
                    // Extract the numeric part of the complaint ID and update the counter if higher
                    int num = Integer.parseInt(data[0].replace("CMP", ""));
                    if (num > counter) counter = num;
                }
            }
        } catch (IOException e) {
            // Silently handle file reading errors (e.g., file not found)
        }
        return complaints;
    }

    /**
     * Saves a list of complaints to the complaints.csv file.
     * Each complaint is written as a single line in CSV format.
     *
     * @param complaints The List of Complaint objects to save
     */
    public static void saveComplaints(List<Complaint> complaints) {
        // Write the complaints to the CSV file
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Complaint complaint : complaints) {
                // Write each complaint as a CSV line using its toString method
                writer.write(complaint.toString() + "\n");
            }
        } catch (IOException e) {
            // Handle file writing errors and print the stack trace
            e.printStackTrace();
        }
    }

    /**
     * Raises a new complaint with the specified details and saves it to the CSV file.
     * Generates a unique complaint ID and sets the initial status to "Pending".
     *
     * @param studentId    The ID of the student raising the complaint
     * @param category     The category of the complaint
     * @param description  The detailed description of the complaint
     * @return The newly created Complaint object
     */
    public static Complaint raiseComplaint(String studentId, String category, String description) {
        // Generate a unique complaint ID (e.g., CMP1001)
        String complaintId = "CMP" + (++counter);
        // Create a new Complaint object with "Pending" status
        Complaint complaint = new Complaint(complaintId, studentId, category, description, "Pending");
        // Load existing complaints
        List<Complaint> complaints = loadComplaints();
        // Add the new complaint to the list
        complaints.add(complaint);
        // Save the updated list to the CSV file
        saveComplaints(complaints);
        return complaint;
    }

    /**
     * Retrieves all complaints associated with a specific student ID.
     * @param studentId The ID of the student whose complaints are to be retrieved
     * @return A List of Complaint objects filed by the specified student
     */
    public static List<Complaint> viewStudentComplaints(String studentId) {
        // Load all complaints
        List<Complaint> complaints = loadComplaints();
        // Initialize a list to store complaints for the specified student
        List<Complaint> studentComplaints = new ArrayList<>();
        // Filter complaints by student ID
        for (Complaint complaint : complaints) {
            if (complaint.getStudentId().equals(studentId)) {
                studentComplaints.add(complaint);
            }
        }
        return studentComplaints;
    }

    /**
     * Retrieves all complaints stored in the system.
     *
     * @return A List of all Complaint objects
     */
    public static List<Complaint> viewAllComplaints() {
        // Load and return all complaints from the CSV file
        return loadComplaints();
    }

    /**
     * Updates the status of a complaint identified by its complaint ID.
     * Saves the updated complaint list to the CSV file.
     *
     * @param complaintId The ID of the complaint to update
     * @param status      The new status to set for the complaint
     * @return true if the complaint was found and updated, false otherwise
     */
    public static boolean updateComplaintStatus(String complaintId, String status) {
        // Load all complaints
        List<Complaint> complaints = loadComplaints();
        // Search for the complaint by ID
        for (Complaint complaint : complaints) {
            if (complaint.getComplaintId().equals(complaintId)) {
                // Update the complaint's status
                complaint.setStatus(status);
                // Save the updated list to the CSV file
                saveComplaints(complaints);
                return true;
            }
        }
        // Return false if the complaint ID was not found
        return false;
    }
}
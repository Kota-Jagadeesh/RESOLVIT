/**
 * Complaint class represents a complaint entity in a complaint management system.
 * It encapsulates details such as complaint ID, student ID, category, description, status,
 * and the manager to whom the complaint is assigned.
 * <p>
 * The class provides:
 * - Constructor to initialize all attributes.
 * - Getter methods to retrieve attribute values.
 * - Setter methods to update status and assigned manager.
 * - toString() method to generate a CSV-compatible representation.
 */
public class Complaint {

    // Unique identifier for the complaint
    private String complaintId;

    // ID of the student who submitted the complaint
    private String studentId;

    // Category of complaint: Hostel, Food, or College
    private String category;

    // Detailed description of the complaint
    private String description;

    // Status of the complaint (e.g., Pending, Forwarded, Resolved)
    private String status;

    // Username of the manager assigned to handle the complaint
    private String assignedManager;

    /**
     * Constructs a Complaint object with all necessary details.
     *
     * @param complaintId     Unique complaint ID
     * @param studentId       ID of the student who filed the complaint
     * @param category        Complaint category (Hostel, Food, College)
     * @param description     Detailed explanation of the issue
     * @param status          Current complaint status
     * @param assignedManager Username of the manager assigned
     */
    public Complaint(String complaintId, String studentId, String category,
                     String description, String status, String assignedManager) {
        this.complaintId = complaintId;
        this.studentId = studentId;
        this.category = category;
        this.description = description;
        this.status = status;
        this.assignedManager = assignedManager;
    }

    // -------- Getter methods --------

    /** @return Unique complaint ID */
    public String getComplaintId() {
        return complaintId;
    }

    /** @return Student ID */
    public String getStudentId() {
        return studentId;
    }

    /** @return Complaint category */
    public String getCategory() {
        return category;
    }

    /** @return Complaint description */
    public String getDescription() {
        return description;
    }

    /** @return Current status of the complaint */
    public String getStatus() {
        return status;
    }

    /** @return Manager assigned to the complaint */
    public String getAssignedManager() {
        return assignedManager;
    }

    // -------- Setter methods --------

    /**
     * Updates the status of the complaint.
     *
     * @param status New complaint status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Assigns or updates the manager handling the complaint.
     *
     * @param assignedManager Manager's username
     */
    public void setAssignedManager(String assignedManager) {
        this.assignedManager = assignedManager;
    }

    /**
     * Converts the complaint object into a CSV string format.
     * Format: complaintId,studentId,category,description,status,assignedManager
     *
     * @return CSV-formatted string representing the complaint
     */
    @Override
    public String toString() {
        return complaintId + "," + studentId + "," + category + "," +
               description + "," + status + "," + assignedManager;
    }
}

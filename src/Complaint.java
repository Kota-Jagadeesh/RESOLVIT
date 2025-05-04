/**
 * Complaint class represents a complaint entity in a complaint management system.
 * It encapsulates details such as complaint ID, student ID, category, description, and status.
 * The class provides getter methods for accessing attributes, a setter method for updating
 * the status, and a toString method for CSV-compatible string representation.
 */
public class Complaint {
    // Instance variables for complaint attributes
    private String complaintId;   // Unique identifier for the complaint
    private String studentId;     // Identifier of the student who filed the complaint
    private String category;      // Category of the complaint (e.g., academic, facility)
    private String description;   // Detailed description of the complaint
    private String status;        // Current status of the complaint (e.g., pending, resolved)

    /**
     * Constructor for Complaint.
     * Initializes a Complaint object with the specified attributes.
     */
    public Complaint(String complaintId, String studentId, String category, String description, String status) {
        // Assign the provided values to instance variables
        this.complaintId = complaintId;
        this.studentId = studentId;
        this.category = category;
        this.description = description;
        this.status = status;
    }
    /**
     * Retrieves the complaint ID.
     */
    public String getComplaintId() { 
        return complaintId; 
    }

    /**
     * Retrieves the student ID.
     */
    public String getStudentId() { 
        return studentId; 
    }

    /**
     * Retrieves the complaint category.
     */
    public String getCategory() { 
        return category; 
    }

    /**
     * Retrieves the complaint description.
     */
    public String getDescription() { 
        return description; 
    }

    /**
     * Retrieves the complaint status.
     */
    public String getStatus() { 
        return status; 
    }

    /**
     * Updates the status of the complaint.
     */
    public void setStatus(String status) { 
        this.status = status; 
    }

    /**
     * Returns a string representation of the complaint in CSV format.
     * The format is: complaintId,studentId,category,description,status.
     *
     * @return A CSV-formatted string representing the complaint
     */
    @Override
    public String toString() {
        return complaintId + "," + studentId + "," + category + "," + description + "," + status;
    }
}
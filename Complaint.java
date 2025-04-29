public class Complaint {
    private String complaintId;
    private String studentId;
    private String category;
    private String description;
    private String status;

    public Complaint(String complaintId, String studentId, String category, String description, String status) {
        this.complaintId = complaintId;
        this.studentId = studentId;
        this.category = category;
        this.description = description;
        this.status = status;
    }

    public String getComplaintId() { return complaintId; }
    public String getStudentId() { return studentId; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return complaintId + "," + studentId + "," + category + "," + description + "," + status;
    }
}
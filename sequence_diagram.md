# Sequence Diagram for Complaint Management System

The Sequence Diagram illustrates the flow of processes within the complaint management system by showing how objects interact over time. This diagram focuses on the scenario where a student raises a complaint.

## Scenario: Student Raises a Complaint

### Actors/Objects Involved
- **Student** (Actor)
- **`StudentDashboard`** (Object)
- **`RaiseComplaintPage`** (Object)
- **`ComplaintManager`** (Object)
- **`Complaint`** (Object)

### Sequence of Interactions
1. **Student → StudentDashboard**: Clicks "Raise Complaint" button.
   - `StudentDashboard` receives the `actionPerformed` event and creates a new `RaiseComplaintPage` instance, passing the `studentUsername`.
   - `StudentDashboard` calls `dispose()` on itself.

2. **StudentDashboard → RaiseComplaintPage**: `new RaiseComplaintPage(studentUsername)` and `setVisible(true)`.
   - The `RaiseComplaintPage` window is displayed to the student.

3. **Student → RaiseComplaintPage**: Enters category and description, clicks "Submit" button.
   - `RaiseComplaintPage` receives the `actionPerformed` event and validates the input fields.

4. **RaiseComplaintPage → ComplaintManager**: `raiseComplaint(studentUsername, category, description)`.
   - `ComplaintManager` generates a new `complaintId` (e.g., "CMP1004") and creates a new `Complaint` object with status "Pending".

5. **ComplaintManager → Complaint**: `new Complaint(complaintId, studentId, category, description, "Pending")`.
   - A new `Complaint` object is instantiated.

6. **ComplaintManager → ComplaintManager**: `loadComplaints()`.
   - `ComplaintManager` loads existing complaints from `complaints.csv`.

7. **ComplaintManager → ComplaintManager**: Adds the new `Complaint` to the list of complaints.

8. **ComplaintManager → ComplaintManager**: `saveComplaints(complaints)`.
   - `ComplaintManager` saves the updated list of complaints to `complaints.csv`.

9. **ComplaintManager → RaiseComplaintPage**: Returns the new `Complaint` object.
   - `RaiseComplaintPage` receives the result and displays a success message: "Complaint submitted successfully!".

10. **RaiseComplaintPage → StudentDashboard**: `new StudentDashboard(studentUsername)` and `setVisible(true)`.
    - `RaiseComplaintPage` creates a new `StudentDashboard` instance and displays it.
    - `RaiseComplaintPage` calls `dispose()` on itself.

### Lifelines
- **Student**: Initiates the process and interacts with the UI.
- **StudentDashboard**: Active until the "Raise Complaint" action, then destroyed.
- **RaiseComplaintPage**: Active during complaint submission, then destroyed.
- **ComplaintManager**: Persists throughout to handle complaint operations.
- **Complaint**: Created during the process and persists in the system.

### Notes
- The sequence assumes successful input validation. If validation fails (e.g., empty fields), `RaiseComplaintPage` would display an error message and not proceed with `ComplaintManager`.
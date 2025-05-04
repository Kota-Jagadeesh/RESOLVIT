# Class Diagram for Complaint Management System

The Class Diagram represents the static structure of the complaint management system, detailing classes, their attributes, methods, and relationships.

## Classes and Attributes

### User
- **Attributes**:
  - `userId: String` (private)
  - `name: String` (private)
  - `role: String` (private)
- **Methods**:
  - `User(userId: String, name: String, role: String)` (constructor)
  - `getUserId(): String`
  - `getName(): String`
  - `getRole(): String`

### Complaint
- **Attributes**:
  - `complaintId: String` (private)
  - `studentId: String` (private)
  - `category: String` (private)
  - `description: String` (private)
  - `status: String` (private)
- **Methods**:
  - `Complaint(complaintId: String, studentId: String, category: String, description: String, status: String)` (constructor)
  - `getComplaintId(): String`
  - `getStudentId(): String`
  - `getCategory(): String`
  - `getDescription(): String`
  - `getStatus(): String`
  - `setStatus(status: String): void`
  - `toString(): String`

### ComplaintManager
- **Attributes**:
  - `FILE_PATH: String` (private, static, final) = "complaints.csv"
  - `counter: int` (private, static) = 1000
- **Methods**:
  - `loadComplaints(): List<Complaint>` (static)
  - `saveComplaints(complaints: List<Complaint>): void` (static)
  - `raiseComplaint(studentId: String, category: String, description: String): Complaint` (static)
  - `viewStudentComplaints(studentId: String): List<Complaint>` (static)
  - `viewAllComplaints(): List<Complaint>` (static)
  - `updateComplaintStatus(complaintId: String, status: String): boolean` (static)

### AdminDashboard (extends JFrame, implements ActionListener)
- **Attributes**:
  - `viewComplaintsButton: JButton` (private)
  - `updateStatusButton: JButton` (private)
  - `addAdminButton: JButton` (private)
  - `logoutButton: JButton` (private)
- **Methods**:
  - `AdminDashboard()` (constructor)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)
  - `main(args: String[]): void` (static)

### AdminLoginPage (extends JFrame, implements ActionListener)
- **Attributes**:
  - `usernameField: JTextField`
  - `passwordField: JPasswordField`
  - `loginButton: JButton`
  - `backButton: JButton`
- **Methods**:
  - `AdminLoginPage()` (constructor)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)

### AdminSignupPage (extends JFrame, implements ActionListener)
- **Attributes**:
  - `usernameField: JTextField`
  - `passwordField: JPasswordField`
  - `signupButton: JButton`
  - `backButton: JButton`
- **Methods**:
  - `AdminSignupPage()` (constructor)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)

### StudentDashboard (extends JFrame, implements ActionListener)
- **Attributes**:
  - `complaintsPane: JTextPane`
  - `raiseComplaintButton: JButton`
  - `viewComplaintsButton: JButton`
  - `logoutButton: JButton`
  - `studentUsername: String`
- **Methods**:
  - `StudentDashboard(username: String)` (constructor)
  - `loadComplaints(): void` (private)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)

### StudentLoginPage (extends JFrame, implements ActionListener)
- **Attributes**:
  - `usernameField: JTextField`
  - `passwordField: JPasswordField`
  - `loginButton: JButton`
  - `signupButton: JButton`
  - `backButton: JButton`
- **Methods**:
  - `StudentLoginPage()` (constructor)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)

### StudentSignupPage (extends JFrame, implements ActionListener)
- **Attributes**:
  - `usernameField: JTextField`
  - `emailField: JTextField`
  - `rollNoField: JTextField`
  - `branchField: JTextField`
  - `passwordField: JPasswordField`
  - `signupButton: JButton`
  - `backButton: JButton`
- **Methods**:
  - `StudentSignupPage()` (constructor)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)

### RaiseComplaintPage (extends JFrame, implements ActionListener)
- **Attributes**:
  - `categoryField: JTextField`
  - `descriptionField: JTextField`
  - `submitButton: JButton`
  - `backButton: JButton`
  - `studentUsername: String`
- **Methods**:
  - `RaiseComplaintPage(studentUsername: String)` (constructor)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)

### UpdateStatusPage (extends JFrame, implements ActionListener)
- **Attributes**:
  - `complaintIdField: JTextField`
  - `statusField: JTextField`
  - `updateButton: JButton`
  - `backButton: JButton`
- **Methods**:
  - `UpdateStatusPage()` (constructor)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)

### ViewComplaintsPage (extends JFrame, implements ActionListener)
- **Attributes**:
  - `complaintsPane: JTextPane`
  - `backButton: JButton`
- **Methods**:
  - `ViewComplaintsPage()` (constructor)
  - `loadComplaints(): void` (private)
  - `actionPerformed(e: ActionEvent): void` (from ActionListener)

## Relationships

### Inheritance
- `AdminDashboard`, `AdminLoginPage`, `AdminSignupPage`, `StudentDashboard`, `StudentLoginPage`, `StudentSignupPage`, `RaiseComplaintPage`, `UpdateStatusPage`, and `ViewComplaintsPage` inherit from `JFrame`.

### Interface Implementation
- `AdminDashboard`, `AdminLoginPage`, `AdminSignupPage`, `StudentDashboard`, `StudentLoginPage`, `StudentSignupPage`, `RaiseComplaintPage`, `UpdateStatusPage`, and `ViewComplaintsPage` implement `ActionListener`.

### Association
- `ComplaintManager` uses a `List<Complaint>` to manage complaints.
- `StudentDashboard`, `RaiseComplaintPage`, `UpdateStatusPage`, and `ViewComplaintsPage` interact with `ComplaintManager` to perform complaint-related operations.
- `AdminLoginPage` and `AdminSignupPage` read/write to `admins.csv`.
- `StudentLoginPage` and `StudentSignupPage` read/write to `students.csv`.
- `ComplaintManager` reads/writes to `complaints.csv`.

## Notes
- The `User` class is a base class but is not extended by other classes in the provided code. It could potentially represent both students and admins, though the system currently uses CSV files for user data.
- The `HomePage` class is referenced but not provided; it is assumed to be a `JFrame` subclass.
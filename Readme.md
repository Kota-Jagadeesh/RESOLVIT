# ResolveIt - Complaint Management System

## What is ResolveIt?

ResolveIt is a simple desktop application built using Java and Swing for managing complaints in a school or college. It allows **students** to raise complaints and **administrators** to view and update the status of those complaints. The system stores user details and complaints in CSV files, making it easy to use without needing a complex database.

The application has two main user types:
- **Students**: Can sign up, log in, raise complaints, and view their own complaints.
- **Admins**: Can sign up, log in, view all complaints, update complaint statuses, and add new admins.

## Features

- **User-Friendly Interface**: Simple and clean GUI built with Java Swing for easy navigation.
- **Student Features**:
  - Sign up with details like username, password, email, roll number, and branch.
  - Log in to access a dashboard.
  - Raise complaints by providing a category and description.
  - View their own complaints with details like ID, category, description, and status.
- **Admin Features**:
  - Sign up with a username and password.
  - Log in to access a dashboard.
  - View all complaints from all students.
  - Update the status of any complaint (e.g., "Pending" to "Resolved").
  - Add new admin accounts.
- **Data Storage**: Uses CSV files (`students.csv`, `admins.csv`, `complaints.csv`) to store user and complaint data.
- **Unique Complaint IDs**: Automatically generates complaint IDs (e.g., CMP1001, CMP1002) for tracking.

## How to Use ResolveIt

1. **Run the Application**:
   - Make sure you have Java installed (JDK 8 or later).
   - Compile and run the `MainApp.java` file to start the application.
   - Example command: `javac MainApp.java && java MainApp`

2. **Home Page**:
   - Choose **Admin Login** or **Student Login** to proceed.
   - Students can also sign up if they don't have an account.

3. **For Students**:
   - **Sign Up**: Fill in username, password, email, roll number, and branch.
   - **Log In**: Use your username and password.
   - **Dashboard**: Raise a new complaint or view your existing complaints.
   - **Logout**: Return to the home page.

4. **For Admins**:
   - **Sign Up**: Create an admin account with a username and password.
   - **Log In**: Use your admin credentials.
   - **Dashboard**: View all complaints, update their statuses, or add a new admin.
   - **Logout**: Return to the home page.

5. **Data Files**:
   - `students.csv`: Stores student details (username, password, email, rollNo, branch).
   - `admins.csv`: Stores admin details (username, password).
   - `complaints.csv`: Stores complaint details (complaintId, studentId, category, description, status).

## Why Should You Use ResolveIt?

- **Easy to Use**: The interface is simple, so anyone can use it without technical knowledge.
- **Organized Complaint Tracking**: Helps schools or colleges manage student complaints efficiently.
- **No Database Needed**: Uses CSV files, so it’s lightweight and doesn’t require setting up a database.
- **Customizable**: The code is open-source and can be modified to fit specific needs.
- **Time-Saving**: Admins can quickly view and resolve complaints, and students can track their issues easily.

ResolveIt is perfect for small institutions or organizations that need a straightforward way to handle complaints without complex software.

## Future Scope of Implementation

ResolveIt can be improved in many ways to make it even better. Here are some ideas for future updates:
- **Database Integration**: Replace CSV files with a database (e.g., MySQL) for faster data handling and better security.
- **Password Security**: Add password encryption to protect user credentials.
- **Email Notifications**: Send emails to students when their complaint status changes.
- **Mobile App**: Create a mobile version of ResolveIt for easier access.
- **Complaint Categories**: Allow admins to define specific categories (e.g., "Hostel", "Academic") for better organization.
- **File Attachments**: Let students attach files (e.g., photos) to their complaints.
- **Analytics Dashboard**: Add a feature for admins to see statistics, like the number of complaints per category or status.
- **Multi-Language Support**: Support different languages to make the app accessible to more users.
- **Role-Based Access**: Add more user roles (e.g., faculty, staff) with specific permissions.

## How to Set Up the Project

1. **Clone or Download**:
   - Download the project files or clone the repository if hosted on GitHub.

2. **Requirements**:
   - Java Development Kit (JDK 8 or later).
   - A Java IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor with command-line tools.

3. **File Structure**:
   - Ensure all Java files (`MainApp.java`, `HomePage.java`, `StudentLoginPage.java`, etc.) are in the same directory.
   - The application will create `students.csv`, `admins.csv`, and `complaints.csv` automatically when needed.

4. **Compile and Run**:
   - Open a terminal in the project directory.
   - Compile: `javac *.java`
   - Run: `java MainApp`

5. **Test the Application**:
   - Try signing up as a student or admin.
   - Raise complaints and check if they appear in the admin’s view.
   - Update complaint statuses and verify changes.

## Project Structure

- **MainApp.java**: Entry point of the application, launches the home page.
- **HomePage.java**: Displays the main menu with admin and student login options.
- **StudentLoginPage.java**: Handles student login and signup navigation.
- **StudentSignupPage.java**: Allows new students to create accounts.
- **StudentDashboard.java**: Student interface for raising and viewing complaints.
- **RaiseComplaintPage.java**: Interface for students to submit new complaints.
- **AdminLoginPage.java**: Handles admin login and signup navigation.
- **AdminSignupPage.java**: Allows new admins to create accounts.
- **AdminDashboard.java**: Admin interface for viewing complaints, updating statuses, and adding admins.
- **UpdateStatusPage.java**: Interface for admins to update complaint statuses.
- **ViewComplaintsPage.java**: Displays all complaints for admins.
- **Complaint.java**: Represents a complaint with attributes like ID, student ID, category, description, and status.
- **User.java**: Represents a user with attributes like ID, name, and role.
- **ComplaintManager.java**: Manages complaint operations (loading, saving, raising, viewing, and updating).

## Limitations

- **No Password Encryption**: Passwords are stored as plain text in CSV files, which is not secure.
- **Basic Validation**: Limited input validation (e.g., no email format check).
- **Single-Threaded**: The app may slow down with many users or complaints due to file-based storage.
- **No Session Management**: Users are logged out when navigating back to the home page.

## Contributing

If you want to improve ResolveIt, feel free to:
- Fix bugs or add new features.
- Suggest ideas for future updates.
- Submit pull requests if the project is hosted on a platform like GitHub.

## License

This project is open-source and available for educational purposes. You can modify and use it as needed, but please give credit to the original developers.

## Contact

For questions or support, reach out to [Your Contact Info, if applicable]. We hope ResolveIt helps make complaint management easier for your institution!

---

*Built with ❤️ for simple and effective complaint management.*
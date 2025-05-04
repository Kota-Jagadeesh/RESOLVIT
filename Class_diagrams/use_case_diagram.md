# Use Case Diagram for Complaint Management System

The Use Case Diagram maps the interactions between users (actors) and the complaint management system, focusing on the system's functionality from a user perspective.

## Actors

### Student
- A user who can log in, sign up, raise complaints, and view their own complaints.

### Admin
- A user who can log in, sign up, view all complaints, update complaint statuses, and add new admins.

### System
- The complaint management system itself (not an actor, but the boundary).

## Use Cases

### Student Use Cases
- **Sign Up**
  - **Actor**: Student
  - **Description**: A student creates a new account by providing username, password, email, roll number, and branch.
- **Log In**
  - **Actor**: Student
  - **Description**: A student logs into the system using their username and password.
- **Raise Complaint**
  - **Actor**: Student
  - **Description**: A student submits a new complaint by specifying a category and description.
- **View My Complaints**
  - **Actor**: Student
  - **Description**: A student views all complaints they have filed, including details like ID, category, description, and status.
- **Log Out**
  - **Actor**: Student
  - **Description**: A student logs out, returning to the home page.

### Admin Use Cases
- **Sign Up**
  - **Actor**: Admin
  - **Description**: An admin creates a new admin account by providing a username and password.
- **Log In**
  - **Actor**: Admin
  - **Description**: An admin logs into the system using their username and password.
- **View All Complaints**
  - **Actor**: Admin
  - **Description**: An admin views all complaints in the system, including details like ID, student ID, category, description, and status.
- **Update Complaint Status**
  - **Actor**: Admin
  - **Description**: An admin updates the status of a specific complaint by providing the complaint ID and new status.
- **Add New Admin**
  - **Actor**: Admin
  - **Description**: An admin adds a new admin account via the signup page.
- **Log Out**
  - **Actor**: Admin
  - **Description**: An admin logs out, returning to the home page.

## Relationships

### Include
- "Log In" is a prerequisite for "Raise Complaint", "View My Complaints", "View All Complaints", "Update Complaint Status", and "Add New Admin".

### Extend
- None identified in the provided code.

## System Boundary
- The system boundary includes all use cases: Sign Up, Log In, Raise Complaint, View My Complaints, View All Complaints, Update Complaint Status, Add New Admin, and Log Out.
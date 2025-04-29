# ResolveIt - Complaint Management System  (GUI)

## Overview

ResolveIt is a Java-based complaint management system designed to facilitate the submission and management of student complaints within an academic institution. Built using Java Swing for the graphical user interface and CSV files for persistent data storage, the system allows students to sign up, log in, raise complaints, and view their own complaints. Administrators can log in, view all complaints, update complaint statuses, and add new admins after logging in. This project is designed to be simple, user-friendly, and suitable for academic purposes, mirroring the structure of a typical event management system.

## Features

- **Student Features**:
  - **Signup**: Students can create an account by providing a username, password, email, roll number, and branch.
  - **Login**: Students can log in using their username and password.
  - **Raise Complaint**: Students can submit complaints by specifying a category and description.
  - **View Complaints**: Students can view their own submitted complaints, including their status.

- **Admin Features**:
  - **Login**: Admins can log in using predefined or dynamically added credentials .
  - **View All Complaints**: Admins can see all complaints submitted by students.
  - **Update Complaint Status**: Admins can update the status of any complaint (e.g., from "Pending" to "Resolved").
  - **Add New Admin**: After logging in, admins can add new admin accounts to the system.

- **Data Storage**:
  - All data (complaints, student accounts, admin accounts) is stored persistently in CSV files.
  - Files used: `complaints.csv`, `students.csv`, and `admins.csv`.

## Prerequisites

- Java 17 
- Visual Studio Code with the "Extension Pack for Java" installed.

## Setup Instructions

1. **Create the Project Structure**:
   - Clone or create the directory structure as shown below.
   - Ensure all `.java` files are placed in the `src/` folder.

2. **Initialize CSV Files**:
   - Create the following CSV files in the project root directory (`resolveit/`):
     - `complaints.csv`: complaintId,studentId,category,description,status
     - `students.csv`: username,password,email,rollNo,branch
     - `admins.csv`: username,password
       admin,admin123
   - Commands to create these files (run in the project root):
     ```bash
     echo "complaintId,studentId,category,description,status" > complaints.csv
     echo "username,password,email,rollNo,branch" > students.csv
     echo "username,password" > admins.csv
     echo "admin,admin123" >> admins.csv

# Running ResolveIt in Visual Studio Code

To run the ResolveIt project in Visual Studio Code using the terminal, follow these steps:

1. Open the terminal in VS Code within the `resolveit/src/` directory.
2. Compile all Java files by running the command: `javac *.java`
3. Run the application by executing: `java MainApp`
# BITS PACKAGE MANAGEMENT

This is a Package Management System that provides functionalities for both Admin and User interactions. It allows admins to manage package deliveries, view reports, and register packages, while users can log in, track their orders, and manage their information.

## Features

### 1. **Admin Login**
   - Admin users can log in using hardcoded credentials.
   - Admin credentials: 
     - Username: `admin`
     - Password: `admin`
   - Upon successful login, the Admin can proceed to the Admin Dashboard.

### 2. **User Login**
   - Existing users can log in using their College ID and password.
   - User credentials are validated from a file (`users.txt`), which stores the user data.
   - After successful login, users are redirected to their dashboard where they can track their orders.

### 3. **New User Registration**
   - New users can create an account by entering their College ID and password.
   - New user data is stored in a file (`users.txt`).

### 4. **Admin Dashboard**
   - After logging in, Admin can access various options:
     - **Manage Deliveries:** View and manage registered packages. Admin can pick up or delete packages.
     - **View Reports:** View a log of recent activities and notifications.
     - **Register Package:** Register a new package for a student.
     - **Logout:** Log out of the Admin Dashboard.

### 5. **User Dashboard**
   - After logging in, users have access to the following options:
     - **Track Order:** Users can check the status of their packages by reading from the `user_reports_<collegeId>.txt` file.
     - **Logout:** Log out from the user dashboard.

### 6. **Package Management**
   - Admins can manage package deliveries:
     - **Pick up Package:** Admin can mark a package as picked up by a specific user.
     - **Delete Package:** Admin can delete a package from the system.
     - **Register Package:** Admin can register a new package with details like Student ID, description, and arrival date.
     - **Notification System:** Notifications are sent to students, friends, and admins based on actions like package pick up.

### 7. **File Storage**
   - The system uses text files to store:
     - User details (`users.txt`)
     - Package details (`packages.txt`)
     - Admin and user reports (`admin_reports.txt` and `user_reports_<collegeId>.txt`)
     - Notifications (`notifications.txt`)

## Technologies Used

- Java Swing: For the graphical user interface (GUI)
- Java I/O: For reading and writing data to files
- BufferedReader, BufferedWriter: For file operations (user and package data)
- SimpleDateFormat: For managing timestamps of activities

## Installation Instructions

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/deba1/Shop-Management-System.git

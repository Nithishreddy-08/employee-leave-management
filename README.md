# Employee Leave Management System

A backend-based Employee Leave Management System developed using Java, Spring Boot, Spring Data JPA, Spring Security, and MySQL.

The system allows employees to apply for leaves, view their leave history and balances, while administrators can manage employees, approve/reject leave requests, view dashboards, and filter leave records.

---

## 🚀 Features

### Employee Management
- Add new employees
- View all employees
- View employee details by ID
- Update employee details
- Delete employees
- Search employees by name

### Leave Management
- Apply for leave
- View all leave requests
- View leave request by ID
- View employee leave history
- Delete leave requests
- Automatic leave status as `PENDING`
- Approve or reject leave requests

### Leave Validation
- Prevents applying for leave with an end date before the start date
- Prevents applying for leave with a past start date
- Checks whether the employee exists
- Prevents overlapping approved leaves
- Checks available leave balance
- Automatically rejects requests when sufficient leave balance is not available
- Calculates total leave days automatically

### Leave Types and Balances

| Leave Type | Annual Balance |
|------------|----------------|
| Casual Leave | 12 days |
| Sick Leave | 10 days |
| Earned Leave | 15 days |

Leave balances are automatically deducted when a leave request is approved.

### Leave Filtering
- Filter leaves by status
- Filter leaves by leave type
- Filter leaves by date range
- View employees who are currently on approved leave

### Admin Dashboard
The admin dashboard provides:
- Total employees
- Total leave requests
- Pending leave requests
- Approved leave requests
- Rejected leave requests

### Authentication and Security
- User registration
- User login
- Password verification
- Role-based authorization
- Admin and Employee roles
- Protected API endpoints
- HTTP Basic Authentication
- Unauthorized users receive `401 Unauthorized`
- Employees attempting admin operations receive `403 Forbidden`

### Automatic Leave Renewal
The system includes an automatic yearly leave balance renewal scheduler.

At the beginning of a new year, employee leave balances are reset to:

- Casual Leave: 12
- Sick Leave: 10
- Earned Leave: 15

---

## 🛠️ Technologies Used

- **Java 24**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **MySQL 8**
- **Maven**
- **IntelliJ IDEA**
- **REST APIs**

---


### One small correction before you paste it

Because your actual project currently uses **`NoOpPasswordEncoder`**, I deliberately did **not** claim that passwords are encrypted. Also, the README says "password verification" rather than "encrypted passwords", which accurately matches your current implementation.

After you paste the README into GitHub, **commit and push it**:

```bash
git add README.md
git commit -m "Added project documentation"
git push

## 📁 Project Structure

```text
employee-leave-management
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.nithish.employee_leave_management
│   │   │       │
│   │   │       ├── controller
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── EmployeeController.java
│   │   │       │   ├── LeaveRequestController.java
│   │   │       │   └── UserController.java
│   │   │       │
│   │   │       ├── dto
│   │   │       │   ├── AdminDashboard.java
│   │   │       │   ├── LeaveSummary.java
│   │   │       │   ├── LoginRequest.java
│   │   │       │   ├── LoginResponse.java
│   │   │       │   └── OnLeaveEmployee.java
│   │   │       │
│   │   │       ├── entity
│   │   │       │   ├── Employee.java
│   │   │       │   ├── LeaveRequest.java
│   │   │       │   ├── LeaveStatus.java
│   │   │       │   └── User.java
│   │   │       │
│   │   │       ├── exception
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │
│   │   │       ├── repository
│   │   │       │   ├── EmployeeRepository.java
│   │   │       │   ├── LeaveRequestRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       │
│   │   │       ├── scheduler
│   │   │       │   └── LeaveBalanceScheduler.java
│   │   │       │
│   │   │       ├── security
│   │   │       │   └── SecurityConfig.java
│   │   │       │
│   │   │       └── service
│   │   │           ├── EmployeeService.java
│   │   │           ├── LeaveRequestService.java
│   │   │           └── UserService.java
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md

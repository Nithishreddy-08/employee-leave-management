package com.nithish.employee_leave_management.dto;

public class OnLeaveEmployee {

    private Long employeeId;
    private String employeeName;
    private String department;
    private String leaveType;
    private String startDate;
    private String endDate;

    public OnLeaveEmployee() {
    }

    public OnLeaveEmployee(
            Long employeeId,
            String employeeName,
            String department,
            String leaveType,
            String startDate,
            String endDate) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
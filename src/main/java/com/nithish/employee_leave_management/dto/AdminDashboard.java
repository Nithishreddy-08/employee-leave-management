package com.nithish.employee_leave_management.dto;

public class AdminDashboard {

    private long totalEmployees;
    private long totalLeaveRequests;
    private long pendingRequests;
    private long approvedRequests;
    private long rejectedRequests;

    public AdminDashboard() {
    }

    public AdminDashboard(
            long totalEmployees,
            long totalLeaveRequests,
            long pendingRequests,
            long approvedRequests,
            long rejectedRequests) {

        this.totalEmployees = totalEmployees;
        this.totalLeaveRequests = totalLeaveRequests;
        this.pendingRequests = pendingRequests;
        this.approvedRequests = approvedRequests;
        this.rejectedRequests = rejectedRequests;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getTotalLeaveRequests() {
        return totalLeaveRequests;
    }

    public void setTotalLeaveRequests(long totalLeaveRequests) {
        this.totalLeaveRequests = totalLeaveRequests;
    }

    public long getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public long getApprovedRequests() {
        return approvedRequests;
    }

    public void setApprovedRequests(long approvedRequests) {
        this.approvedRequests = approvedRequests;
    }

    public long getRejectedRequests() {
        return rejectedRequests;
    }

    public void setRejectedRequests(long rejectedRequests) {
        this.rejectedRequests = rejectedRequests;
    }
}
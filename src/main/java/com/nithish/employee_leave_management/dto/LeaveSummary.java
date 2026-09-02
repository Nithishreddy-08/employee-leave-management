package com.nithish.employee_leave_management.dto;

public class LeaveSummary {

    private Long employeeId;

    private long totalRequests;
    private long pendingRequests;
    private long approvedRequests;
    private long rejectedRequests;

    private int casualLeaveBalance;
    private int sickLeaveBalance;
    private int earnedLeaveBalance;

    public LeaveSummary() {
    }

    public LeaveSummary(
            Long employeeId,
            long totalRequests,
            long pendingRequests,
            long approvedRequests,
            long rejectedRequests,
            int casualLeaveBalance,
            int sickLeaveBalance,
            int earnedLeaveBalance) {

        this.employeeId = employeeId;
        this.totalRequests = totalRequests;
        this.pendingRequests = pendingRequests;
        this.approvedRequests = approvedRequests;
        this.rejectedRequests = rejectedRequests;
        this.casualLeaveBalance = casualLeaveBalance;
        this.sickLeaveBalance = sickLeaveBalance;
        this.earnedLeaveBalance = earnedLeaveBalance;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
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

    public int getCasualLeaveBalance() {
        return casualLeaveBalance;
    }

    public void setCasualLeaveBalance(int casualLeaveBalance) {
        this.casualLeaveBalance = casualLeaveBalance;
    }

    public int getSickLeaveBalance() {
        return sickLeaveBalance;
    }

    public void setSickLeaveBalance(int sickLeaveBalance) {
        this.sickLeaveBalance = sickLeaveBalance;
    }

    public int getEarnedLeaveBalance() {
        return earnedLeaveBalance;
    }

    public void setEarnedLeaveBalance(int earnedLeaveBalance) {
        this.earnedLeaveBalance = earnedLeaveBalance;
    }
}
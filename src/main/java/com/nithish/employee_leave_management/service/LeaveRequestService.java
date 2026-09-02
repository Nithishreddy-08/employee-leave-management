package com.nithish.employee_leave_management.service;
import java.time.temporal.ChronoUnit;
import com.nithish.employee_leave_management.entity.Employee;
import com.nithish.employee_leave_management.entity.LeaveRequest;
import com.nithish.employee_leave_management.entity.LeaveStatus;
import com.nithish.employee_leave_management.repository.EmployeeRepository;
import com.nithish.employee_leave_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;
import com.nithish.employee_leave_management.dto.LeaveSummary;
import com.nithish.employee_leave_management.dto.AdminDashboard;
import java.time.LocalDate;
import java.util.List;
import com.nithish.employee_leave_management.dto.OnLeaveEmployee;
import java.util.ArrayList;
@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestService(
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository) {

        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    // Employee applies for leave
    public LeaveRequest applyForLeave(
            Long employeeId,
            String leaveType,
            LocalDate startDate,
            LocalDate endDate,
            String reason) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Start date cannot be in the past");
        }
        boolean overlappingLeave = leaveRequestRepository
                .existsByEmployeeIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        employeeId,
                        LeaveStatus.APPROVED,
                        endDate,
                        startDate
                );

        if (overlappingLeave) {
            throw new IllegalArgumentException(
                    "Leave request overlaps with an already approved leave"
            );
        }
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with ID: " + employeeId));

        if (startDate.isAfter(endDate)) {
            throw new RuntimeException(
                    "Start date cannot be after end date");
        }

        LeaveRequest leaveRequest = new LeaveRequest();

        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setStartDate(startDate);
        leaveRequest.setEndDate(endDate);
        leaveRequest.setReason(reason);
        leaveRequest.setTotalDays(totalDays);
        // Automatically store today's date
        leaveRequest.setAppliedDate(LocalDate.now());

        // Every new request starts as PENDING
        leaveRequest.setStatus(LeaveStatus.PENDING);

        return leaveRequestRepository.save(leaveRequest);
    }

    // Get all leave requests
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    // Get leave request by ID
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave request not found with ID: " + id));
    }

    // Get leave history of an employee
    public List<LeaveRequest> getEmployeeLeaveHistory(Long employeeId) {

        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException(
                    "Employee not found with ID: " + employeeId);
        }

        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    // Approve leave
    // Approve leave
    public LeaveRequest approveLeave(Long leaveId) {

        LeaveRequest leaveRequest = getLeaveRequestById(leaveId);
        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Only PENDING leave requests can be approved"
            );
        }
        Employee employee = leaveRequest.getEmployee();

        long totalDays = leaveRequest.getTotalDays();

        if (leaveRequest.getLeaveType().equalsIgnoreCase("CASUAL")) {

            if (employee.getCasualLeaveBalance() < totalDays) {
                leaveRequest.setStatus(LeaveStatus.REJECTED);
                leaveRequestRepository.save(leaveRequest);

                throw new IllegalArgumentException(
                        "Insufficient casual leave balance. Leave request has been rejected."
                );
            }

            employee.setCasualLeaveBalance(
                    employee.getCasualLeaveBalance() - (int) totalDays
            );

        } else if (leaveRequest.getLeaveType().equalsIgnoreCase("SICK")) {

            if (employee.getSickLeaveBalance() < totalDays) {
                leaveRequest.setStatus(LeaveStatus.REJECTED);
                leaveRequestRepository.save(leaveRequest);

                throw new IllegalArgumentException(
                        "Insufficient sick leave balance. Leave request has been rejected."
                );
            }

            employee.setSickLeaveBalance(
                    employee.getSickLeaveBalance() - (int) totalDays
            );

        } else if (leaveRequest.getLeaveType().equalsIgnoreCase("EARNED")) {

            if (employee.getEarnedLeaveBalance() < totalDays) {
                leaveRequest.setStatus(LeaveStatus.REJECTED);
                leaveRequestRepository.save(leaveRequest);

                throw new IllegalArgumentException(
                        "Insufficient earned leave balance. Leave request has been rejected."
                );
            }

            employee.setEarnedLeaveBalance(
                    employee.getEarnedLeaveBalance() - (int) totalDays
            );

        } else {
            throw new IllegalArgumentException(
                    "Invalid leave type: " + leaveRequest.getLeaveType()
            );
        }

        leaveRequest.setStatus(LeaveStatus.APPROVED);

        employeeRepository.save(employee);

        return leaveRequestRepository.save(leaveRequest);
    }

    // Reject leave
    // Reject leave
    public LeaveRequest rejectLeave(Long leaveId) {

        LeaveRequest leaveRequest = getLeaveRequestById(leaveId);

        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Only PENDING leave requests can be rejected"
            );
        }

        leaveRequest.setStatus(LeaveStatus.REJECTED);

        return leaveRequestRepository.save(leaveRequest);
    }

    public void deleteLeaveRequest(Long id) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        leaveRequestRepository.delete(leaveRequest);
    }

    public LeaveSummary getLeaveSummary(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with ID: " + employeeId));

        List<LeaveRequest> leaveRequests =
                leaveRequestRepository.findByEmployeeId(employeeId);

        long totalRequests = leaveRequests.size();

        long pendingRequests = leaveRequests.stream()
                .filter(leave -> leave.getStatus() == LeaveStatus.PENDING)
                .count();

        long approvedRequests = leaveRequests.stream()
                .filter(leave -> leave.getStatus() == LeaveStatus.APPROVED)
                .count();

        long rejectedRequests = leaveRequests.stream()
                .filter(leave -> leave.getStatus() == LeaveStatus.REJECTED)
                .count();

        return new LeaveSummary(
                employeeId,
                totalRequests,
                pendingRequests,
                approvedRequests,
                rejectedRequests,
                employee.getCasualLeaveBalance(),
                employee.getSickLeaveBalance(),
                employee.getEarnedLeaveBalance()
        );
    }

    // Get admin dashboard summary
    public AdminDashboard getAdminDashboard() {

        long totalEmployees = employeeRepository.count();

        List<LeaveRequest> leaveRequests =
                leaveRequestRepository.findAll();

        long totalLeaveRequests = leaveRequests.size();

        long pendingRequests = leaveRequests.stream()
                .filter(leave -> leave.getStatus() == LeaveStatus.PENDING)
                .count();

        long approvedRequests = leaveRequests.stream()
                .filter(leave -> leave.getStatus() == LeaveStatus.APPROVED)
                .count();

        long rejectedRequests = leaveRequests.stream()
                .filter(leave -> leave.getStatus() == LeaveStatus.REJECTED)
                .count();

        return new AdminDashboard(
                totalEmployees,
                totalLeaveRequests,
                pendingRequests,
                approvedRequests,
                rejectedRequests
        );
    }

    // Get employees currently on approved leave
    public List<OnLeaveEmployee> getCurrentlyOnLeaveEmployees() {

        LocalDate today = LocalDate.now();

        List<LeaveRequest> leaveRequests =
                leaveRequestRepository.findAll();

        List<OnLeaveEmployee> onLeaveEmployees = new ArrayList<>();

        for (LeaveRequest leaveRequest : leaveRequests) {

            if (leaveRequest.getStatus() == LeaveStatus.APPROVED
                    && !today.isBefore(leaveRequest.getStartDate())
                    && !today.isAfter(leaveRequest.getEndDate())) {

                Employee employee = leaveRequest.getEmployee();

                OnLeaveEmployee employeeData = new OnLeaveEmployee(
                        employee.getId(),
                        employee.getName(),
                        employee.getDepartment(),
                        leaveRequest.getLeaveType(),
                        leaveRequest.getStartDate().toString(),
                        leaveRequest.getEndDate().toString()
                );

                onLeaveEmployees.add(employeeData);
            }
        }

        return onLeaveEmployees;
    }

    // Filter leave requests by status
    public List<LeaveRequest> getLeavesByStatus(LeaveStatus status) {

        return leaveRequestRepository.findByStatus(status);
    }

    // Filter leave requests by leave type
    public List<LeaveRequest> getLeavesByType(String leaveType) {

        return leaveRequestRepository.findByLeaveTypeIgnoreCase(leaveType);
    }
    // Filter leave requests by date range
    public List<LeaveRequest> getLeavesByDateRange(
            LocalDate startDate,
            LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date"
            );
        }

        return leaveRequestRepository.findByStartDateBetween(
                startDate,
                endDate
        );
    }
}
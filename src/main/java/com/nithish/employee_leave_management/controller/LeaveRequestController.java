package com.nithish.employee_leave_management.controller;
import com.nithish.employee_leave_management.dto.OnLeaveEmployee;
import com.nithish.employee_leave_management.entity.LeaveRequest;
import com.nithish.employee_leave_management.entity.LeaveStatus;
import com.nithish.employee_leave_management.service.LeaveRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nithish.employee_leave_management.dto.LeaveSummary;
import com.nithish.employee_leave_management.dto.AdminDashboard;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    // Employee applies for leave
    @PostMapping
    public ResponseEntity<LeaveRequest> applyForLeave(
            @RequestParam Long employeeId,
            @RequestParam String leaveType,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam String reason) {

        LeaveRequest leaveRequest = leaveRequestService.applyForLeave(
                employeeId,
                leaveType,
                startDate,
                endDate,
                reason
        );

        return ResponseEntity.ok(leaveRequest);
    }

    // Get all leave requests
    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        return ResponseEntity.ok(
                leaveRequestService.getAllLeaveRequests()
        );
    }

    // Get leave request by ID
    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService.getLeaveRequestById(id)
        );
    }

    // Get leave history of an employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getEmployeeLeaveHistory(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                leaveRequestService.getEmployeeLeaveHistory(employeeId)
        );
    }

    // Approve leave
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService.approveLeave(id)
        );
    }

    // Reject leave
    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService.rejectLeave(id)
        );
    }

    // Delete leave request
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeaveRequest(
            @PathVariable Long id) {

        leaveRequestService.deleteLeaveRequest(id);

        return ResponseEntity.ok("Leave request deleted successfully");
    }

    // Get leave summary of an employee
    @GetMapping("/summary/{employeeId}")
    public ResponseEntity<LeaveSummary> getLeaveSummary(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                leaveRequestService.getLeaveSummary(employeeId)
        );
    }

    // Get admin dashboard
    @GetMapping("/admin/dashboard")
    public ResponseEntity<AdminDashboard> getAdminDashboard() {

        return ResponseEntity.ok(
                leaveRequestService.getAdminDashboard()
        );
    }

    // Get employees currently on leave
    @GetMapping("/on-leave")
    public ResponseEntity<List<OnLeaveEmployee>> getCurrentlyOnLeaveEmployees() {

        return ResponseEntity.ok(
                leaveRequestService.getCurrentlyOnLeaveEmployees()
        );
    }

    // Filter leaves by status
    @GetMapping("/filter/status")
    public ResponseEntity<List<LeaveRequest>> getLeavesByStatus(
            @RequestParam LeaveStatus status) {

        return ResponseEntity.ok(
                leaveRequestService.getLeavesByStatus(status)
        );
    }

    // Filter leaves by leave type
    @GetMapping("/filter/type")
    public ResponseEntity<List<LeaveRequest>> getLeavesByType(
            @RequestParam String leaveType) {

        return ResponseEntity.ok(
                leaveRequestService.getLeavesByType(leaveType)
        );
    }
    // Filter leaves by date range
    @GetMapping("/filter/date")
    public ResponseEntity<List<LeaveRequest>> getLeavesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return ResponseEntity.ok(
                leaveRequestService.getLeavesByDateRange(
                        startDate,
                        endDate
                )
        );
    }
}
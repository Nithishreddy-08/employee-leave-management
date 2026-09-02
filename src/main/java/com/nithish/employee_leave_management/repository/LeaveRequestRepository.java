package com.nithish.employee_leave_management.repository;

import com.nithish.employee_leave_management.entity.LeaveRequest;
import com.nithish.employee_leave_management.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeId(Long employeeId);

    List<LeaveRequest> findByStatus(LeaveStatus status);

    List<LeaveRequest> findByLeaveTypeIgnoreCase(String leaveType);

    List<LeaveRequest> findByStartDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    boolean existsByEmployeeIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long employeeId,
            LeaveStatus status,
            LocalDate endDate,
            LocalDate startDate
    );
}
package com.nithish.employee_leave_management.scheduler;

import com.nithish.employee_leave_management.entity.Employee;
import com.nithish.employee_leave_management.repository.EmployeeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Component
public class LeaveBalanceScheduler {

    private final EmployeeRepository employeeRepository;

    public LeaveBalanceScheduler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Check for yearly leave renewal every day at midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void renewLeaveBalances() {

        int currentYear = Year.now().getValue();

        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {

            if (employee.getLeaveBalanceYear() < currentYear) {

                employee.setCasualLeaveBalance(12);
                employee.setSickLeaveBalance(10);
                employee.setEarnedLeaveBalance(15);

                employee.setLeaveBalanceYear(currentYear);
            }
        }

        employeeRepository.saveAll(employees);
    }
}
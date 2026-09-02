package com.nithish.employee_leave_management.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
@JsonPropertyOrder({
        "id",
        "name",
        "email",
        "department",
        "role",
        "casualLeaveBalance",
        "sickLeaveBalance",
        "earnedLeaveBalance",
        "leaveBalanceYear"
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String department;

    private String role;

    @Column(nullable = false)
    private int casualLeaveBalance = 12;

    @Column(nullable = false)
    private int sickLeaveBalance = 10;

    @Column(nullable = false)
    private int earnedLeaveBalance = 15;

    private int leaveBalanceYear = 2026;

    public Employee() {
    }

    public Employee(String name, String email, String department, String role) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public int getLeaveBalanceYear() {
        return leaveBalanceYear;
    }

    public void setLeaveBalanceYear(int leaveBalanceYear) {
        this.leaveBalanceYear = leaveBalanceYear;
    }
}
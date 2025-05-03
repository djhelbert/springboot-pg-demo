package com.employee.demo.api;

public class ProjectMemberRole {
    private Long projectId;
    private Long employeeId;
    private String role;

    public ProjectMemberRole() {}

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

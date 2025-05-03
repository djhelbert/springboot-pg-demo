package com.employee.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project_members")
public class ProjectMember {

    private long id;
    private Project project;
    private Employee employee;
    private String role;

    public ProjectMember() {}

    public ProjectMember(String role, Project project, Employee employee) {
        this.role = role;
        this.project = project;
        this.employee = employee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Column(name = "role", nullable = false, length=10)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", role=" + role + ", project=" + project.getId() + ", employee=" + employee.getId() + "}";
    }
}

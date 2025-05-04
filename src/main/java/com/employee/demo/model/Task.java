package com.employee.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    public static final String OPEN = "OPEN";
    public static final String CLOSED = "CLOSED";

    private Long id;
    private String status;
    private Project project;
    private Employee employee;
    private String name;
    private String description;

    public Task() {}

    public Task(String status, Project project, Employee employee, String name, String description) {
        this.status = status;
        this.project = project;
        this.employee = employee;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "status", nullable = false, length=10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "task_name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", status='" + status +
                ", project=" + project.getId() + ", employee=" + employee.getId() +
                ", name='" + name + ", description='" + description + '}';
    }

    public static class Builder {
        Task task = new Task();

        public Task build() {
            return task;
        }

        public Builder status(String status) {
            task.setStatus(status);
            return this;
        }

        public Builder name(String name) {
            task.setName(name);
            return this;
        }

        public Builder description(String description) {
            task.setDescription(description);
            return this;
        }

        public Builder employee(Employee employee) {
            task.setEmployee(employee);
            return this;
        }

        public Builder project(Project project) {
            task.setProject(project);
            return this;
        }
    }
}

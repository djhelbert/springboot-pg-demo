package com.employee.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    public static String OPEN = "NEW";
    public static String STARTED = "STARTED";
    public static String COMPLETE = "COMPLETE";

    private long id;
    private String name;
    private String description;
    private String code;
    private String status;

    public Project() {
    }

    public Project(Long id) {
        this.id = id;
    }

    public Project(String name, String description, String code, String status) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "proj_name", nullable = false, length = 50)
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

    @Column(name = "code", nullable = false, length = 4)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "status", nullable = false, length=10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", code=" + code + ", status=" + status + "}";
    }
}

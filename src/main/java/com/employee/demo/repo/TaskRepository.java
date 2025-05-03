package com.employee.demo.repo;

import com.employee.demo.model.Employee;
import com.employee.demo.model.Project;
import com.employee.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject(Project project);

    List<Task> findByProjectAndStatus(Project project, String status);

    List<Task> findByEmployee(Employee employee);

    List<Task> findByEmployeeAndStatus(Employee employee, String status);
}

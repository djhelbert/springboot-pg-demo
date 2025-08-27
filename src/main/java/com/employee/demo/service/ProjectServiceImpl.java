package com.employee.demo.service;

import com.employee.demo.exception.ExceptionFactory;
import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.ProjectMember;
import com.employee.demo.model.Task;
import com.employee.demo.repo.EmployeeRepository;
import com.employee.demo.repo.ProjectMemberRepository;
import com.employee.demo.repo.ProjectRepository;
import com.employee.demo.repo.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectMemberRepository projMemRepo;
  @Autowired
  private ProjectRepository projectRepo;
  @Autowired
  private EmployeeRepository employeeRepo;
  @Autowired
  private TaskRepository taskRepo;

  public void deleteProjectMembership(Long id) {
    projMemRepo.deleteById(id);
  }

  public Task closeTask(Long id) throws ResourceNotFoundException {
    final Optional<Task> optional = taskRepo.findById(id);
    if (optional.isPresent()) {
      optional.get().setStatus(Task.CLOSED);
      return taskRepo.save(optional.get());
    } else {
      throw ExceptionFactory.taskNotFound(id);
    }
  }

  public Task updateTask(Long id, String status, Long employeeId, String name, String description)
      throws ResourceNotFoundException {
    final Optional<Task> optional = taskRepo.findById(id);

    if (optional.isPresent()) {
      final Task task = optional.get();
      task.setStatus(status);
      task.setName(name);
      task.setDescription(description);
      task.setEmployee(employeeRepo.findById(employeeId)
          .orElseThrow(() -> ExceptionFactory.employeeNotFound(employeeId)));

      return taskRepo.save(task);
    } else {
      throw ExceptionFactory.taskNotFound(id);
    }
  }

  public Task addTask(Long projectId, Long employeeId, String name, String description)
      throws ResourceNotFoundException {
    return taskRepo.save(new Task.Builder().name(name).status(Task.OPEN).description(description)
        .project(projectRepo.findById(projectId)
            .orElseThrow(() -> ExceptionFactory.projectNotFound(projectId)))
        .employee(employeeRepo.findById(employeeId)
            .orElseThrow(() -> ExceptionFactory.employeeNotFound(employeeId)))
        .build()
    );
  }

  public void deleteTask(Long id) {
    taskRepo.deleteById(id);
  }

  public ProjectMember addEmployeeToProject(String role, Long projectId, Long employeeId)
      throws ResourceNotFoundException {
    return projMemRepo.save(new ProjectMember(role, projectRepo.findById(projectId)
        .orElseThrow(() -> ExceptionFactory.projectNotFound(projectId)),
        employeeRepo.findById(employeeId)
            .orElseThrow(() -> ExceptionFactory.employeeNotFound(employeeId))));
  }

  public List<Task> allEmployeeTasks(Long id) throws ResourceNotFoundException {
    return taskRepo.findByEmployee(
        employeeRepo.findById(id).orElseThrow(() -> ExceptionFactory.employeeNotFound(id)));
  }

  public List<Task> employeeTasksByStatus(Long id, String status) throws ResourceNotFoundException {
    return taskRepo.findByEmployeeAndStatus(
        employeeRepo.findById(id).orElseThrow(() -> ExceptionFactory.employeeNotFound(id)), status);
  }

  public List<ProjectMember> projectMembers(Long projectId) throws ResourceNotFoundException {
    return projMemRepo.findByProject(projectRepo.findById(projectId)
        .orElseThrow(() -> ExceptionFactory.projectNotFound(projectId)));
  }
}

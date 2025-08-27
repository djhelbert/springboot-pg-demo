package com.employee.demo.service;

import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.ProjectMember;
import com.employee.demo.model.Task;
import java.util.List;

public interface ProjectService {
  void deleteProjectMembership(Long id) ;

  Task closeTask(Long id) throws ResourceNotFoundException;

  Task updateTask(Long id, String status, Long employeeId, String name, String description) throws ResourceNotFoundException;

  Task addTask(Long projectId, Long employeeId, String name, String description) throws ResourceNotFoundException;

  void deleteTask(Long id);

  ProjectMember addEmployeeToProject(String role, Long projectId, Long employeeId) throws ResourceNotFoundException;

  List<Task> allEmployeeTasks(Long id) throws ResourceNotFoundException;

  List<Task> employeeTasksByStatus(Long id, String status) throws ResourceNotFoundException;

  List<ProjectMember> projectMembers(Long projectId) throws ResourceNotFoundException;
}

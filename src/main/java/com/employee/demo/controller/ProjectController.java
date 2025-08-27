package com.employee.demo.controller;

import com.employee.demo.exception.ExceptionFactory;
import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.Project;
import com.employee.demo.model.ProjectMember;
import com.employee.demo.repo.ProjectRepository;
import com.employee.demo.service.ProjectService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

  @Autowired
  private ProjectRepository projectRepo;
  @Autowired
  private ProjectService projectService;

  @GetMapping("/projects")
  public List<Project> getAllProjects() {
    return projectRepo.findAll();
  }

  @GetMapping("/projects/{id}")
  public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long id)
      throws ResourceNotFoundException {
    var project = projectRepo.findById(id).orElseThrow(() -> ExceptionFactory.projectNotFound(id));
    return ResponseEntity.ok().body(project);
  }

  @PostMapping("/project")
  public Project createProject(@Valid @RequestBody Project project) {
    return projectRepo.save(project);
  }

  @PutMapping("/projects/{id}")
  public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long id,
      @Valid @RequestBody Project projDetails) throws ResourceNotFoundException {
    var project = projectRepo.findById(id).orElseThrow(() -> ExceptionFactory.projectNotFound(id));

    project.setName(projDetails.getName());
    project.setDescription(projDetails.getDescription());
    project.setCode(projDetails.getCode());
    project.setStatus(projDetails.getStatus());

    var updatedProject = projectRepo.save(project);
    return ResponseEntity.ok(updatedProject);
  }

  @DeleteMapping("/projects/{id}")
  public Map<String, Boolean> deleteProject(@PathVariable(value = "id") Long id)
      throws ResourceNotFoundException {
    var project = projectRepo.findById(id).orElseThrow(() -> ExceptionFactory.projectNotFound(id));

    projectRepo.delete(project);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }

  @GetMapping("/project_members/{id}")
  public List<ProjectMember> projectMembers(@PathVariable(value = "id") Long id)
      throws ResourceNotFoundException {
    return projectService.projectMembers(id);
  }
}

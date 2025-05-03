package com.employee.demo.service;

import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.Employee;
import com.employee.demo.model.Project;
import com.employee.demo.model.ProjectMember;
import com.employee.demo.repo.EmployeeRepository;
import com.employee.demo.repo.ProjectMemberRepository;
import com.employee.demo.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectMemberRepository projMemRepo;
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private EmployeeRepository employeeRepo;

    public void deleteProjectMembership(Long id) {
        projMemRepo.deleteById(id);
    }

    public ProjectMember addEmployeeToProject(String role, Long projectId, Long employeeId) {
        return projMemRepo.save(new ProjectMember(role, projectRepo.findById(projectId).get(), employeeRepo.findById(employeeId).get()));
    }
}

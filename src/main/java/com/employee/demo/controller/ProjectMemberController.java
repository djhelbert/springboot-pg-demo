package com.employee.demo.controller;

import com.employee.demo.api.ProjectMemberRole;
import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.ProjectMember;
import com.employee.demo.service.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProjectMemberController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @PostMapping("/add_project_member")
    public ProjectMember addProjectMember(@Valid @RequestBody ProjectMemberRole pmr) throws ResourceNotFoundException {
        return projectServiceImpl.addEmployeeToProject(pmr.getRole(), pmr.getProjectId(), pmr.getEmployeeId());
    }

    @DeleteMapping("/project_member/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        projectServiceImpl.deleteProjectMembership(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

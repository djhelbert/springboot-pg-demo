package com.employee.demo.controller;

import com.employee.demo.api.TaskStatus;
import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.Task;
import com.employee.demo.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/add_task")
    public Task addTask(@Valid @RequestBody TaskStatus ts) throws ResourceNotFoundException {
        return projectService.addTask(ts.getProjectId(), ts.getEmployeeId(), ts.getName(), ts.getDescription());
    }

    @PostMapping("/update_task")
    public Task updateTask(@Valid @RequestBody TaskStatus ts) throws ResourceNotFoundException {
        return projectService.updateTask(ts.getId(), ts.getStatus(), ts.getEmployeeId(), ts.getName(), ts.getDescription());
    }

    @DeleteMapping("/task/{id}")
    public Map<String, Boolean> deleteTask(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        projectService.deleteTask(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

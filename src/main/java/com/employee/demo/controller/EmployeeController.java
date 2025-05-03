package com.employee.demo.controller;

import com.employee.demo.exception.ExceptionFactory;
import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.Employee;
import com.employee.demo.repo.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        var employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> ExceptionFactory.employeeNotFound(id));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setPhone(employeeDetails.getPhone());

        final Employee updatedEmployee = employeeRepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        var employee = employeeRepo.findById(id).orElseThrow(() -> ExceptionFactory.employeeNotFound(id));
        employeeRepo.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

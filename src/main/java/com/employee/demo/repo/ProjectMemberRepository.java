package com.employee.demo.repo;

import com.employee.demo.model.Employee;
import com.employee.demo.model.Project;
import com.employee.demo.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    /**
     * Find Project Members by Project
     * @param project Project
     * @return List of project members
     */
    List<ProjectMember> findByProject(Project project);

    /**
     * Find Employee Project Memberships
     * @param employee Employee
     * @return List of project memberships
     */
    List<ProjectMember> findByEmployee(Employee employee);
}

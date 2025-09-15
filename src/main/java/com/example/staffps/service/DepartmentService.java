package com.example.staffps.service;

import com.example.staffps.model.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Optional<Department> getDepartmentById(Long id);
    Department createDepartment(Department department);
    Department updateDepartment(Long id, Department departmentDetails);
    void deleteDepartment(Long id);
    List<Department> getDepartmentsByCollege(Long collegeId);
    boolean existsByDepartmentName(String departmentName);
}
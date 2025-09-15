package com.example.staffps.service;

import com.example.staffps.model.Staff;
import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> getAllStaff();
    Optional<Staff> getStaffById(Long id);
    Optional<Staff> getStaffByEmail(String email);
    Staff createStaff(Staff staff);
    Staff updateStaff(Long id, Staff staffDetails);
    void deleteStaff(Long id);
    List<Staff> getStaffByDepartment(Long departmentId);
    boolean existsByEmail(String email);
}
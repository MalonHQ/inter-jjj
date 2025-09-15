package com.example.staffps.repository;

import com.example.staffps.model.PermissionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermissionRequestRepository extends JpaRepository<PermissionRequest, Long> {
    List<PermissionRequest> findByStaffId(Long staffId);
    List<PermissionRequest> findByStaffDepartmentId(Long departmentId);
    List<PermissionRequest> findByStatus(String status);
    List<PermissionRequest> findByStaffDepartmentCollegeId(Long collegeId);
}
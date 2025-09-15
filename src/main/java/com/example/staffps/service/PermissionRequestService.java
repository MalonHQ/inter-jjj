package com.example.staffps.service;

import com.example.staffps.model.PermissionRequest;
import java.util.List;

public interface PermissionRequestService {
    List<PermissionRequest> getAllPermissionRequests();
    PermissionRequest getPermissionRequestById(Long id);
    PermissionRequest createPermissionRequest(PermissionRequest permissionRequest);
    PermissionRequest updatePermissionRequest(Long id, PermissionRequest permissionRequestDetails);
    void deletePermissionRequest(Long id);
    List<PermissionRequest> getPermissionRequestsByStaff(Long staffId);
    List<PermissionRequest> getPermissionRequestsByDepartment(Long departmentId);
    List<PermissionRequest> getPermissionRequestsByCollege(Long collegeId);
    List<PermissionRequest> getPermissionRequestsByStatus(String status);
}
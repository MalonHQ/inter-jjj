package com.example.staffps.serviceimpl;


import com.example.staffps.model.PermissionRequest;
import com.example.staffps.repository.PermissionRequestRepository;
import com.example.staffps.service.PermissionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PermissionRequestServiceImpl implements PermissionRequestService {

    @Autowired
    private PermissionRequestRepository permissionRequestRepository;

    @Override
    public List<PermissionRequest> getAllPermissionRequests() {
        return permissionRequestRepository.findAll();
    }

    @Override
    public PermissionRequest getPermissionRequestById(Long id) {
        return permissionRequestRepository.findById(id).orElse(null);
    }

    @Override
    public PermissionRequest createPermissionRequest(PermissionRequest permissionRequest) {
        return permissionRequestRepository.save(permissionRequest);
    }

    @Override
    public PermissionRequest updatePermissionRequest(Long id, PermissionRequest permissionRequestDetails) {
        PermissionRequest permissionRequest = permissionRequestRepository.findById(id).orElse(null);
        if (permissionRequest != null) {
            permissionRequest.setStaff(permissionRequestDetails.getStaff());
            permissionRequest.setRequestDate(permissionRequestDetails.getRequestDate());
            permissionRequest.setStartDate(permissionRequestDetails.getStartDate());
            permissionRequest.setEndDate(permissionRequestDetails.getEndDate());
            permissionRequest.setPermissionType(permissionRequestDetails.getPermissionType());
            permissionRequest.setReason(permissionRequestDetails.getReason());
            permissionRequest.setStatus(permissionRequestDetails.getStatus());
            return permissionRequestRepository.save(permissionRequest);
        }
        return null;
    }

    @Override
    public void deletePermissionRequest(Long id) {
        permissionRequestRepository.deleteById(id);
    }

    @Override
    public List<PermissionRequest> getPermissionRequestsByStaff(Long staffId) {
        return permissionRequestRepository.findByStaffId(staffId);
    }

    @Override
    public List<PermissionRequest> getPermissionRequestsByDepartment(Long departmentId) {
        return permissionRequestRepository.findByStaffDepartmentId(departmentId);
    }

    @Override
    public List<PermissionRequest> getPermissionRequestsByCollege(Long collegeId) {
        return permissionRequestRepository.findByStaffDepartmentCollegeId(collegeId);
    }

    @Override
    public List<PermissionRequest> getPermissionRequestsByStatus(String status) {
        return permissionRequestRepository.findByStatus(status);
    }
}
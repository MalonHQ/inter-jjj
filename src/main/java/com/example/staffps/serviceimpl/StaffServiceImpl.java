package com.example.staffps.serviceimpl;

import com.example.staffps.model.Staff;
import com.example.staffps.repository.StaffRepository;
import com.example.staffps.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Optional<Staff> getStaffByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Long id, Staff staffDetails) {
        Optional<Staff> optionalStaff = staffRepository.findById(id);
        if (optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            staff.setStaffName(staffDetails.getStaffName());
            staff.setEmail(staffDetails.getEmail());
            staff.setPhoneNumber(staffDetails.getPhoneNumber());
            staff.setDepartment(staffDetails.getDepartment());
            return staffRepository.save(staff);
        }
        return null;
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public List<Staff> getStaffByDepartment(Long departmentId) {
        return staffRepository.findByDepartmentId(departmentId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return staffRepository.existsByEmail(email);
    }
}
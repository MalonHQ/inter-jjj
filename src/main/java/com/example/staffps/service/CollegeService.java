package com.example.staffps.service;

import com.example.staffps.model.College;
import java.util.List;
import java.util.Optional;

public interface CollegeService {
    List<College> getAllColleges();
    Optional<College> getCollegeById(Long id);
    College createCollege(College college);
    College updateCollege(Long id, College collegeDetails);
    void deleteCollege(Long id);
    boolean existsByCollegeName(String collegeName);
}
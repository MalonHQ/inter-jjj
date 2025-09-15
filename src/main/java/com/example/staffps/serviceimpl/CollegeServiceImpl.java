package com.example.staffps.serviceimpl;

import com.example.staffps.model.College;
import com.example.staffps.repository.CollegeRepository;
import com.example.staffps.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    @Override
    public Optional<College> getCollegeById(Long id) {
        return collegeRepository.findById(id);
    }

    @Override
    public College createCollege(College college) {
        return collegeRepository.save(college);
    }

    @Override
    public College updateCollege(Long id, College collegeDetails) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();
            college.setCollegeName(collegeDetails.getCollegeName());
            return collegeRepository.save(college);
        }
        return null;
    }

    @Override
    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

    @Override
    public boolean existsByCollegeName(String collegeName) {
        return collegeRepository.existsByCollegeName(collegeName);
    }
}
package com.example.staffps.controller;

import com.example.staffps.model.College;
import com.example.staffps.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @GetMapping
    public List<College> getAllColleges() {
        return collegeService.getAllColleges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<College> getCollegeById(@PathVariable Long id) {
        Optional<College> college = collegeService.getCollegeById(id);
        return college.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public College createCollege(@RequestBody College college) {
        return collegeService.createCollege(college);
    }

    @PutMapping("/{id}")
    public ResponseEntity<College> updateCollege(@PathVariable Long id, @RequestBody College collegeDetails) {
        College updatedCollege = collegeService.updateCollege(id, collegeDetails);
        return updatedCollege != null ? ResponseEntity.ok(updatedCollege) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollege(@PathVariable Long id) {
        collegeService.deleteCollege(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/check-name/{collegeName}")
    public ResponseEntity<Boolean> checkCollegeNameExists(@PathVariable String collegeName) {
        boolean exists = collegeService.existsByCollegeName(collegeName);
        return ResponseEntity.ok(exists);
    }
}
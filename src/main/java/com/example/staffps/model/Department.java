package com.example.staffps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Column(name = "department_name", nullable = false, unique = true)
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "college_id", nullable = false)
    private College college;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Staff> staff = new ArrayList<>();

    // Constructors
    public Department() {}

    public Department(String departmentName, College college) {
        this.departmentName = departmentName;
        this.college = college;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public College getCollege() { return college; }
    public void setCollege(College college) { this.college = college; }

    public List<Staff> getStaff() { return staff; }
    public void setStaff(List<Staff> staff) { this.staff = staff; }

}
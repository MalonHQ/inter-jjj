package com.example.staffps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permission_type")
public class PermissionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Permission name is required")
    @Column(name = "permission_name", nullable = false, unique = true)
    private String permissionName;

    @Column(name = "description", length = 500)
    private String description;
    @OneToMany(mappedBy = "permissionType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PermissionRequest> permissionRequests = new ArrayList<>();
    // Constructors
    public PermissionType() {}

    public PermissionType(String permissionName, String description) {
        this.permissionName = permissionName;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPermissionName() { return permissionName; }
    public void setPermissionName(String permissionName) { this.permissionName = permissionName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<PermissionRequest> getPermissionRequests() { return permissionRequests; }
    public void setPermissionRequests(List<PermissionRequest> permissionRequests) {
        this.permissionRequests = permissionRequests;
    }
}
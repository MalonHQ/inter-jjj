package com.example.staffps.dto;

import java.time.LocalDate;

public class PermissionRequestDTO {
    private Long staffId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long permissionTypeId;
    private String reason;

    // Constructors
    public PermissionRequestDTO() {}

    public PermissionRequestDTO(Long staffId, LocalDate startDate, LocalDate endDate,
                                Long permissionTypeId, String reason) {
        this.staffId = staffId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.permissionTypeId = permissionTypeId;
        this.reason = reason;
    }

    // Getters and Setters
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Long getPermissionTypeId() { return permissionTypeId; }
    public void setPermissionTypeId(Long permissionTypeId) { this.permissionTypeId = permissionTypeId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
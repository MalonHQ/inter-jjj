package com.example.staffps.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "approval")
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private PermissionRequest permissionRequest;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = false)
    private Staff approvedBy;

    @NotBlank(message = "Decision is required")
    @Column(name = "decision", nullable = false)
    private String decision; // APPROVED, REJECTED

    @Column(name = "comment", length = 1000)
    private String comment;

    @Column(name = "decision_date", nullable = false)
    private LocalDate decisionDate;

    @Column(name = "approval_level", nullable = false)
    private String approvalLevel; // DEPARTMENT, COLLEGE

    // Constructors
    public Approval() {}

    public Approval(PermissionRequest permissionRequest, Staff approvedBy,
                    String decision, String comment, LocalDate decisionDate, String approvalLevel) {
        this.permissionRequest = permissionRequest;
        this.approvedBy = approvedBy;
        this.decision = decision;
        this.comment = comment;
        this.decisionDate = decisionDate;
        this.approvalLevel = approvalLevel;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PermissionRequest getPermissionRequest() { return permissionRequest; }
    public void setPermissionRequest(PermissionRequest permissionRequest) {
        this.permissionRequest = permissionRequest;
    }

    public Staff getApprovedBy() { return approvedBy; }
    public void setApprovedBy(Staff approvedBy) { this.approvedBy = approvedBy; }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDate getDecisionDate() { return decisionDate; }
    public void setDecisionDate(LocalDate decisionDate) { this.decisionDate = decisionDate; }

    public String getApprovalLevel() { return approvalLevel; }
    public void setApprovalLevel(String approvalLevel) { this.approvalLevel = approvalLevel; }
}
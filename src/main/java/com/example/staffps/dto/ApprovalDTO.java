package com.example.staffps.dto;

import java.time.LocalDate;

public class ApprovalDTO {
    private Long requestId;
    private Long approvedById;
    private String decision;
    private String comment;
    private String approvalLevel;

    // Constructors
    public ApprovalDTO() {}

    public ApprovalDTO(Long requestId, Long approvedById, String decision,
                       String comment, String approvalLevel) {
        this.requestId = requestId;
        this.approvedById = approvedById;
        this.decision = decision;
        this.comment = comment;
        this.approvalLevel = approvalLevel;
    }

    // Getters and Setters
    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public Long getApprovedById() { return approvedById; }
    public void setApprovedById(Long approvedById) { this.approvedById = approvedById; }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getApprovalLevel() { return approvalLevel; }
    public void setApprovalLevel(String approvalLevel) { this.approvalLevel = approvalLevel; }
}
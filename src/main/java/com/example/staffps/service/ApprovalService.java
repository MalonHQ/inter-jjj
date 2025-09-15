package com.example.staffps.service;

import com.example.staffps.model.Approval;
import java.util.List;

public interface ApprovalService {
    List<Approval> getAllApprovals();
    Approval getApprovalById(Long id);
    Approval createApproval(Approval approval);
    Approval updateApproval(Long id, Approval approvalDetails);
    void deleteApproval(Long id);
    List<Approval> getApprovalsByRequest(Long requestId);
    List<Approval> getApprovalsByApprover(Long staffId);
    List<Approval> getApprovalsByLevel(String approvalLevel);
}
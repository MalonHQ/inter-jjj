package com.example.staffps.serviceimpl;

import com.example.staffps.model.Approval;
import com.example.staffps.repository.ApprovalRepository;
import com.example.staffps.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    @Override
    public List<Approval> getAllApprovals() {
        return approvalRepository.findAll();
    }

    @Override
    public Approval getApprovalById(Long id) {
        return approvalRepository.findById(id).orElse(null);
    }

    @Override
    public Approval createApproval(Approval approval) {
        return approvalRepository.save(approval);
    }

    @Override
    public Approval updateApproval(Long id, Approval approvalDetails) {
        Approval approval = approvalRepository.findById(id).orElse(null);
        if (approval != null) {
            approval.setPermissionRequest(approvalDetails.getPermissionRequest());
            approval.setApprovedBy(approvalDetails.getApprovedBy());
            approval.setDecision(approvalDetails.getDecision());
            approval.setComment(approvalDetails.getComment());
            approval.setDecisionDate(approvalDetails.getDecisionDate());
            approval.setApprovalLevel(approvalDetails.getApprovalLevel());
            return approvalRepository.save(approval);
        }
        return null;
    }

    @Override
    public void deleteApproval(Long id) {
        approvalRepository.deleteById(id);
    }

    @Override
    public List<Approval> getApprovalsByRequest(Long requestId) {
        return approvalRepository.findByPermissionRequestId(requestId);
    }

    @Override
    public List<Approval> getApprovalsByApprover(Long staffId) {
        return approvalRepository.findByApprovedById(staffId);
    }

    @Override
    public List<Approval> getApprovalsByLevel(String approvalLevel) {
        return approvalRepository.findByApprovalLevel(approvalLevel);
    }
}
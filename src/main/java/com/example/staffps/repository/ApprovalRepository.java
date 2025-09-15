package com.example.staffps.repository;

import  com.example.staffps.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    List<Approval> findByPermissionRequestId(Long requestId);
    List<Approval> findByApprovedById(Long staffId);
    List<Approval> findByApprovalLevel(String approvalLevel);
}
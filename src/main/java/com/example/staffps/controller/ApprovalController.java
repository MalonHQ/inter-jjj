package com.example.staffps.controller;

import com.example.staffps.model.Approval;
import com.example.staffps.model.PermissionRequest;
import com.example.staffps.model.Staff;
import com.example.staffps.service.ApprovalService;
import com.example.staffps.service.PermissionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/approvals")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private PermissionRequestService permissionRequestService;

    @GetMapping("/pending")
    public String pendingApprovals(HttpSession session, Model model) {
        Staff user = (Staff) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // In a real application, you would determine the user's role
        // and fetch the appropriate pending requests
        List<PermissionRequest> pendingRequests;

        // For demo purposes, we'll show all pending requests
        pendingRequests = permissionRequestService.getPermissionRequestsByStatus("PENDING");
        pendingRequests.addAll(permissionRequestService.getPermissionRequestsByStatus("DEPARTMENT_APPROVED"));

        model.addAttribute("pendingRequests", pendingRequests);
        return "pending-approvals";
    }

    @GetMapping("/review/{requestId}")
    public String reviewRequest(@PathVariable Long requestId, HttpSession session, Model model) {
        Staff user = (Staff) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        PermissionRequest request = permissionRequestService.getPermissionRequestById(requestId);
        if (request == null) {
            return "redirect:/approvals/pending";
        }

        model.addAttribute("request", request);
        return "review-request";
    }

    @PostMapping("/decide/{requestId}")
    public String makeDecision(@PathVariable Long requestId,
                               @RequestParam String decision,
                               @RequestParam String comment,
                               @RequestParam String approvalLevel,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        Staff user = (Staff) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            PermissionRequest request = permissionRequestService.getPermissionRequestById(requestId);
            if (request == null) {
                redirectAttributes.addFlashAttribute("error", "Request not found");
                return "redirect:/approvals/pending";
            }

            Approval approval = new Approval();
            approval.setPermissionRequest(request);
            approval.setApprovedBy(user);
            approval.setDecision(decision);
            approval.setComment(comment);
            approval.setDecisionDate(LocalDate.now());
            approval.setApprovalLevel(approvalLevel);

            // Update the request status based on the decision
            if ("DEPARTMENT".equals(approvalLevel)) {
                if ("APPROVED".equals(decision)) {
                    request.setStatus("DEPARTMENT_APPROVED");
                } else {
                    request.setStatus("REJECTED");
                }
            } else if ("COLLEGE".equals(approvalLevel)) {
                if ("APPROVED".equals(decision)) {
                    request.setStatus("APPROVED");
                } else {
                    request.setStatus("REJECTED");
                }
            }

            approvalService.createApproval(approval);
            permissionRequestService.updatePermissionRequest(requestId, request);

            redirectAttributes.addFlashAttribute("message", "Decision submitted successfully");
            return "redirect:/approvals/pending";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to submit decision: " + e.getMessage());
            return "redirect:/approvals/review/" + requestId;
        }
    }
}
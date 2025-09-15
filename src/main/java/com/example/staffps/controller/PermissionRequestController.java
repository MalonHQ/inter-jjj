package com.example.staffps.controller;

import com.example.staffps.model.PermissionRequest;
import com.example.staffps.model.Staff;
import com.example.staffps.model.PermissionType;
import com.example.staffps.service.PermissionRequestService;
import com.example.staffps.service.PermissionTypeService;
import com.example.staffps.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/requests")
public class PermissionRequestController {

    @Autowired
    private PermissionRequestService permissionRequestService;

    @Autowired
    private PermissionTypeService permissionTypeService;

    @Autowired
    private StaffService staffService;

    @GetMapping
    public String listRequests(HttpSession session, Model model) {
        Staff user = (Staff) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<PermissionRequest> requests = permissionRequestService.getPermissionRequestsByStaff(user.getId());
        model.addAttribute("requests", requests);
        return "requests";
    }

    @GetMapping("/new")
    public String newRequestForm(HttpSession session, Model model) {
        Staff user = (Staff) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<PermissionType> permissionTypes = permissionTypeService.getAllPermissionTypes();
        model.addAttribute("permissionTypes", permissionTypes);
        model.addAttribute("request", new PermissionRequest());
        return "request-form";
    }

    @PostMapping
    public String createRequest(@ModelAttribute PermissionRequest request,
                                @RequestParam Long permissionTypeId,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        Staff user = (Staff) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            PermissionType permissionType = permissionTypeService.getPermissionTypeById(permissionTypeId).orElse(null);
            if (permissionType == null) {
                redirectAttributes.addFlashAttribute("error", "Invalid permission type selected");
                return "redirect:/requests/new";
            }

            request.setStaff(user);
            request.setPermissionType(permissionType);
            request.setRequestDate(LocalDate.now());
            request.setStatus("PENDING");

            permissionRequestService.createPermissionRequest(request);
            redirectAttributes.addFlashAttribute("message", "Permission request submitted successfully!");
            return "redirect:/requests";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to submit request: " + e.getMessage());
            return "redirect:/requests/new";
        }
    }

    @GetMapping("/{id}")
    public String viewRequest(@PathVariable Long id, HttpSession session, Model model) {
        Staff user = (Staff) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        PermissionRequest request = permissionRequestService.getPermissionRequestById(id);
        if (request == null || !request.getStaff().getId().equals(user.getId())) {
            RedirectAttributes redirectAttributes = null;
            redirectAttributes.addFlashAttribute("error", "Request not found or access denied");
            return "redirect:/requests";
        }

        model.addAttribute("request", request);
        return "request-detail";
    }
}
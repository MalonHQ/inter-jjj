package com.example.staffps.controller;

import com.example.staffps.model.Staff;
import com.example.staffps.model.Department;
import com.example.staffps.model.College;
import com.example.staffps.service.StaffService;
import com.example.staffps.service.DepartmentService;
import com.example.staffps.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CollegeService collegeService;

    @GetMapping("/")
    public String home(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        // In a real application, you would validate credentials properly
        // For this demo, we'll use a simple check
        Staff staff = staffService.getStaffByEmail(email).orElse(null);

        if (staff != null) {
            // Simple authentication - in real app, use password encoder
            if (password.equals("password")) { // Default password for demo
                session.setAttribute("user", staff);
                return "redirect:/dashboard";
            }
        }

        redirectAttributes.addFlashAttribute("error", "Invalid email or password");
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }

        List<College> colleges = collegeService.getAllColleges();
        List<Department> departments = departmentService.getAllDepartments();

        model.addAttribute("colleges", colleges);
        model.addAttribute("departments", departments);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Staff staff,
                           @RequestParam Long departmentId,
                           RedirectAttributes redirectAttributes) {
        try {
            Department department = departmentService.getDepartmentById(departmentId).orElse(null);
            if (department == null) {
                redirectAttributes.addFlashAttribute("error", "Invalid department selected");
                return "redirect:/register";
            }

            staff.setDepartment(department);
            staffService.createStaff(staff);
            redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Staff user = (Staff) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Add user-specific data to the model
        model.addAttribute("user", user);
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
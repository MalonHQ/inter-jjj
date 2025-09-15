package com.example.staffps.controller;

import com.example.staffps.model.PermissionType;
import com.example.staffps.service.PermissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permission-types")
public class PermissionTypeController {

    @Autowired
    private PermissionTypeService permissionTypeService;

    @GetMapping
    public List<PermissionType> getAllPermissionTypes() {
        return permissionTypeService.getAllPermissionTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionType> getPermissionTypeById(@PathVariable Long id) {
        Optional<PermissionType> permissionType = permissionTypeService.getPermissionTypeById(id);
        return permissionType.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PermissionType createPermissionType(@RequestBody PermissionType permissionType) {
        return permissionTypeService.createPermissionType(permissionType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionType> updatePermissionType(@PathVariable Long id, @RequestBody PermissionType permissionTypeDetails) {
        PermissionType updatedPermissionType = permissionTypeService.updatePermissionType(id, permissionTypeDetails);
        return updatedPermissionType != null ? ResponseEntity.ok(updatedPermissionType) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermissionType(@PathVariable Long id) {
        permissionTypeService.deletePermissionType(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/check-name/{permissionName}")
    public ResponseEntity<Boolean> checkPermissionNameExists(@PathVariable String permissionName) {
        boolean exists = permissionTypeService.existsByPermissionName(permissionName);
        return ResponseEntity.ok(exists);
    }
}
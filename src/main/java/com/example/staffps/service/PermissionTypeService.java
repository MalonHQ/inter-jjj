package com.example.staffps.service;

import com.example.staffps.model.PermissionType;
import java.util.List;
import java.util.Optional;

public interface PermissionTypeService {
    List<PermissionType> getAllPermissionTypes();
    Optional<PermissionType> getPermissionTypeById(Long id);
    PermissionType createPermissionType(PermissionType permissionType);
    PermissionType updatePermissionType(Long id, PermissionType permissionTypeDetails);
    void deletePermissionType(Long id);
    boolean existsByPermissionName(String permissionName);
}
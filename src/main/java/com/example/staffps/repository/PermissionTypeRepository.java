package com.example.staffps.repository;

import com.example.staffps.model.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PermissionTypeRepository extends JpaRepository<PermissionType, Long> {
    Optional<PermissionType> findByPermissionName(String permissionName);
    boolean existsByPermissionName(String permissionName);
}
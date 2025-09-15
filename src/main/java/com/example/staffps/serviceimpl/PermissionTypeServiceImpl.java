package com.example.staffps.serviceimpl;

import com.example.staffps.model.PermissionType;
import com.example.staffps.repository.PermissionTypeRepository;
import com.example.staffps.service.PermissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionTypeServiceImpl implements PermissionTypeService {

    @Autowired
    private PermissionTypeRepository permissionTypeRepository;

    @Override
    public List<PermissionType> getAllPermissionTypes() {
        return permissionTypeRepository.findAll();
    }

    @Override
    public Optional<PermissionType> getPermissionTypeById(Long id) {
        return permissionTypeRepository.findById(id);
    }

    @Override
    public PermissionType createPermissionType(PermissionType permissionType) {
        return permissionTypeRepository.save(permissionType);
    }

    @Override
    public PermissionType updatePermissionType(Long id, PermissionType permissionTypeDetails) {
        Optional<PermissionType> optionalPermissionType = permissionTypeRepository.findById(id);
        if (optionalPermissionType.isPresent()) {
            PermissionType permissionType = optionalPermissionType.get();
            permissionType.setPermissionName(permissionTypeDetails.getPermissionName());
            permissionType.setDescription(permissionTypeDetails.getDescription());
            return permissionTypeRepository.save(permissionType);
        }
        return null;
    }

    @Override
    public void deletePermissionType(Long id) {
        permissionTypeRepository.deleteById(id);
    }

    @Override
    public boolean existsByPermissionName(String permissionName) {
        return permissionTypeRepository.existsByPermissionName(permissionName);
    }
}
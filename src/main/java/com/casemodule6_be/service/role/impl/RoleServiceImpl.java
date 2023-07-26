package com.casemodule6_be.service.role.impl;

import com.casemodule6_be.common.enums.RoleName;
import com.casemodule6_be.model.Role;
import com.casemodule6_be.repository.RoleRepository;
import com.casemodule6_be.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}

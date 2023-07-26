package com.casemodule6_be.service.role;

import com.casemodule6_be.common.enums.RoleName;
import com.casemodule6_be.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName name);
}

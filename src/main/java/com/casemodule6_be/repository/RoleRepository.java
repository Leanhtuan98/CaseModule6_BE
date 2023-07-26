package com.casemodule6_be.repository;

import com.casemodule6_be.common.enums.RoleName;
import com.casemodule6_be.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);

}

package com.codegym.repository.login;

import com.codegym.entity.login.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}

package com.codegym.repository.login;

import com.codegym.entity.login.AppRole;
import com.codegym.entity.login.AppUser;
import com.codegym.entity.login.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByAppUser(AppUser appUser);



    Page<UserRole> findAllByAppRole(Pageable pageable, AppRole appRole);
}

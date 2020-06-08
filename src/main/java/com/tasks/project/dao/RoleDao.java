package com.tasks.project.dao;

import com.tasks.project.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<AppRole, Long> {

    public AppRole findByRoleName(String roleName);
}

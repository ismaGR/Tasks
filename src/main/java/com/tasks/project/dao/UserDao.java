package com.tasks.project.dao;

import com.tasks.project.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String Username);
}

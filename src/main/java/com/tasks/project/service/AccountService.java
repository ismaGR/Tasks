package com.tasks.project.service;

import com.tasks.project.entities.AppRole;
import com.tasks.project.entities.AppUser;

import java.util.List;

public interface AccountService {

    public AppUser saveUser(AppUser appUser);

    public AppRole saveRole(AppRole appRole);

    public void addRoleToUser(String usernanme, String role);

    public AppUser findUserByUsername(String username);

    public List<AppUser> findAll();
    
    public AppRole findRoleByRoleName(String roleName);
}

package com.tasks.project.service;

import com.tasks.project.dao.RoleDao;
import com.tasks.project.dao.UserDao;
import com.tasks.project.entities.AppRole;
import com.tasks.project.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public AppUser saveUser(AppUser appUser) {
        String hashPwd = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(hashPwd);
        return userDao.save(appUser);
    }

    @Override
    public AppRole saveRole(AppRole appRole) {

        return roleDao.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole roler = roleDao.findByRoleName(roleName);
        AppUser useer = userDao.findByUsername(username);
        useer.getRoles().add(roler);
    }


    @Override
    public AppUser findUserByUsername(String username) {

        return userDao.findByUsername(username);
    }

    @Override
    public List<AppUser> findAll() {
        return userDao.findAll();
    }

	@Override
	public AppRole findRoleByRoleName(String roleName) {
		
		return roleDao.findByRoleName(roleName);
	}
}

package com.tasks.project.controller;


import com.tasks.project.dao.TaskDao;
import com.tasks.project.entities.AppUser;
import com.tasks.project.entities.Tasks;
import com.tasks.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskControlleur {

    @Autowired
    public AccountService ac;

    @Autowired
    public TaskDao tskDao;

    @PostMapping("/register")
    public AppUser privateUilisateur(@RequestBody RegisterForm rfUsr){

        if(!rfUsr.getPassword().equals(rfUsr.getRePassword()))
            throw new RuntimeException("You must confirm your password");

        AppUser uz = ac.findUserByUsername(rfUsr.getUsername());
        if(uz!=null) throw new RuntimeException("this user already exists!");

        AppUser usr = new AppUser();
        usr.setPassword(rfUsr.getPassword());   usr.setUsername(rfUsr.getUsername());
        ac.saveUser(usr);
        ac.addRoleToUser(rfUsr.getUsername(),"USER");

        return usr;
    }

    @GetMapping("/tasks")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Tasks> getUsers(){

        return tskDao.findAll();
    }

    @PostMapping("/task")
    @PreAuthorize("hasRole('ADMIN')")
    public Tasks getByTaskname(@PathVariable  String taskname){

        return tskDao.findByTaskname(taskname);
    }
}

package com.tasks.project;

import com.tasks.project.dao.RoleDao;
import com.tasks.project.dao.TaskDao;
import com.tasks.project.dao.UserDao;
import com.tasks.project.entities.AppRole;
import com.tasks.project.entities.AppUser;
import com.tasks.project.entities.Tasks;
import com.tasks.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProjectApplication implements CommandLineRunner {

    @Autowired
    private TaskDao tskDao;

    @Autowired
    private UserDao usrDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AccountService acSrv;

    @Bean
    public BCryptPasswordEncoder getBPCE(){

        return new BCryptPasswordEncoder();
    }


    @Override
    public void run(String... args) throws Exception {
        acSrv.saveUser(new AppUser("ismail","54"));
        acSrv.saveUser(new AppUser("stephane","0123"));
        acSrv.saveUser(new AppUser("yohan","012"));
        acSrv.saveUser(new AppUser("manon","01"));
        acSrv.saveUser(new AppUser("julien","0"));

        acSrv.saveRole(new AppRole(null,"USER"));
        acSrv.saveRole(new AppRole(null,"ADMIN"));
     //   acSrv.saveRole(new AppRole(null,"GESTIONNAIRE"));

        acSrv.addRoleToUser("ismail","AGENT");
        acSrv.addRoleToUser("ismail","GESTIONNAIRE");
        acSrv.addRoleToUser("stephane","ADMIN");
        acSrv.addRoleToUser("yohan","GESTIONNAIRE");
        acSrv.addRoleToUser("manon","GESTIONNAIRE");
        acSrv.addRoleToUser("julien","GESTIONNAIRE");


        AppUser u = usrDao.findByUsername("stephane");
        u.getRoles().add(roleDao.findByRoleName("ADMIN"));
        AppUser uz = usrDao.findByUsername("ismail");
        uz.getRoles().add(roleDao.findByRoleName("USER"));
        usrDao.save(uz);

        Stream.of("T1","T2","T3").forEach(t->{
            tskDao.save( new Tasks(null,t));
        });
        tskDao.findAll().forEach(t->{
            System.out.println(t.getTaskname());
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}

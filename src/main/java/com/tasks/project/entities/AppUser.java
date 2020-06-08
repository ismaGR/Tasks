package com.tasks.project.entities;

import com.fasterxml.jackson.annotation.JsonSetter;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity 
public class AppUser {


    @Id
    @GeneratedValue
    private Long id;

    public AppUser() {
		super();
	}

	private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();

    public AppUser(String username, String password) {
        this.username=username;
        this.password=password;
    }
    /*    Accesseur */

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
    }
}

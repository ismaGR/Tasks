package com.tasks.project.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tasks {

    @Id
    @GeneratedValue
    private Long id;


    private String taskname;


	public String getTaskname() {
		return taskname;
	}


	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}


	public Long getId() {
		return id;
	}


	public Tasks() {
		super();
	}


	public Tasks(Long id, String taskname) {
		super();
		this.id = id;
		this.taskname = taskname;
	}
	
    
    
}

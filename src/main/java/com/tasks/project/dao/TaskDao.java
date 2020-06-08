package com.tasks.project.dao;

import com.tasks.project.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Tasks, Long> {

    public Tasks findByTaskname(String taskName);
}

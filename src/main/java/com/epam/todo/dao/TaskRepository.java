package com.epam.todo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.epam.todo.dto.Task;

@RepositoryRestResource
public interface TaskRepository extends JpaRepository<Task, Integer> {
	List<Task> findByCompleted(boolean completed);
	
}

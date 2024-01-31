package com.epam.todo.dao;

import java.util.List;

import com.epam.todo.dto.Task;

public interface TaskDao {

	List<Task> getTasks();

	Task addTask(int id, String name, String description);

	Task modifyTask(int id, String name, String description, boolean completed);

	Task deleteTask(int id);

	Task getTask(int id);
}

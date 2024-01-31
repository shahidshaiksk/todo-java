package com.epam.todo.service;

import java.util.List;

import com.epam.todo.dto.Task;

public interface TaskService {

	List<Task> getTasks();

	List<Task> getCompletedTasks();

	List<Task> getIncompleteTasks();

	Task addTask(String name, String description);

	Task modifyTask(int id, String name, String description, boolean completed);

	Task completeTask(int id);

	Task incompleteTask(int id);

	Task deleteTask(int id);

	Task getTask(int id);

}

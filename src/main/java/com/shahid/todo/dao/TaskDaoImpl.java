package com.epam.todo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.todo.dto.Task;

@Repository
public class TaskDaoImpl implements TaskDao {
	@Autowired
	private List<Task> tasks;
	
	@Override
	public List<Task> getTasks() {
		return this.tasks;
	}
	
	@Override
	public Task addTask(int id, String name, String description) {
		return addTask(new Task(id, name, description, false));
	}
	
	private Task addTask(Task task) {
		tasks.add(task);
		return task;
	}
	
	@Override
	public Task getTask(int id) {
		return tasks.stream().filter(task -> task.getId() == id).findFirst().get();
	}
	
	@Override
	public Task modifyTask(int id, String name, String description, boolean completed) {
		Task modifiableTask = getTask(id);
		if(modifiableTask != null) {
			modifiableTask.setName(name != null ? name : modifiableTask.getName());
			modifiableTask.setDescription(description != null ? description : modifiableTask.getDescription());
			modifiableTask.setCompleted(completed);
		}
		return modifiableTask;
	}

	
	@Override
	public Task deleteTask(int id) {
		Task deletableTask = getTask(id);
		if(deletableTask != null) {
			tasks.remove(deletableTask);
		}
		return deletableTask;
	}
}

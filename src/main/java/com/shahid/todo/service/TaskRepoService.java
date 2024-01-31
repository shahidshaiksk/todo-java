package com.epam.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.todo.dao.TaskRepository;
import com.epam.todo.dto.Task;

@Service
public class TaskRepoService implements TaskService{
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
	
	@Override
	public List<Task> getCompletedTasks(){
		return taskRepository.findByCompleted(true);
	}
	
	@Override
	public List<Task> getIncompleteTasks(){
		return taskRepository.findByCompleted(false);
	}

	@Override
	public Task addTask(String name, String description) {
		Task task = new Task();
		task.setName(name);
		task.setDescription(description);
		return taskRepository.save(task);
		
	}

	@Override
	public Task modifyTask(int id, String name, String description, boolean completed) {
		Task task = taskRepository.findById(id).get();
		task.setName(name);
		task.setDescription(description);
		task.setCompleted(completed);
		return taskRepository.save(task);
	}

	@Override
	public Task completeTask(int id) {
		Task task = taskRepository.findById(id).get();
		task.setCompleted(true);
		return taskRepository.save(task);
	}

	@Override
	public Task incompleteTask(int id) {
		Task task = taskRepository.findById(id).get();
		task.setCompleted(false);
		return taskRepository.save(task);
	}

	@Override
	public Task deleteTask(int id) {
		Task task = taskRepository.findById(id).get();
		taskRepository.deleteById(id);
		return task;
	}

	@Override
	public Task getTask(int id) {
		return taskRepository.findById(id).get();
	}

}

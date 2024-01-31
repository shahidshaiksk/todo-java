package com.epam.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.todo.dao.TaskDao;
import com.epam.todo.dto.Task;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public List<Task> getTasks() {
		return taskDao.getTasks();
	}
	
	@Override
	public Task getTask(int id) {
		return taskDao.getTask(id);
	}
	
	@Override
	public Task addTask(String name, String description) {
		return taskDao.addTask(taskDao.getTasks().size(), name, description);
	}
	
	@Override
	public Task modifyTask(int id, String name, String description, boolean completed) {
		return taskDao.modifyTask(id, name, description, completed);
	}
	
	@Override
	public Task completeTask(int id) {
		return taskDao.modifyTask(id, null, null, true);
	}
	
	@Override
	public Task incompleteTask(int id) {
		return taskDao.modifyTask(id, null, null, false);
	}
	
	@Override
	public Task deleteTask(int id) {
		return taskDao.deleteTask(id);
	}

	@Override
	public List<Task> getCompletedTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getIncompleteTasks() {
		// TODO Auto-generated method stub
		return null;
	}
}

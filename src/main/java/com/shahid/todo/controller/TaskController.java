package com.epam.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.todo.dto.Task;
import com.epam.todo.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	
	@Autowired
	@Qualifier("taskRepoService")
	TaskService taskService;
	
	@GetMapping("/all")
	public List<Task> getTasks() {
		return taskService.getTasks();
	}
	
	@GetMapping("/completed")
	public List<Task> getCompletedTasks(){
		return taskService.getCompletedTasks();
	}
	
	@GetMapping("/incomplete")
	public List<Task> getInompleteTasks(){
		return taskService.getIncompleteTasks();
	}
	
	@GetMapping("/{id}")
	public Task getTask(@PathVariable("id") int id) {
		return taskService.getTask(id);
	}
	
	@PostMapping("/add")
	public Task addTask(@RequestBody Task task) {
		return taskService.addTask(task.getName(), task.getDescription());
	}
	
	@PutMapping("/modify")
	public Task modifyTask(@RequestBody Task task) {
		return taskService.modifyTask(task.getId(), task.getName(), task.getDescription(), task.isCompleted());
	}
	
	@PutMapping("/{id}/complete")
	public Task completeTask(@PathVariable("id") int id){
		return taskService.completeTask(id);
	}
	
	@PutMapping("/{id}/incomplete")
	public Task incompleteTask(@PathVariable("id") int id) {
		return taskService.incompleteTask(id);
	}
	
	@DeleteMapping("/{id}/delete")
	public Task deleteTask(@PathVariable("id") int id) {
		return taskService.deleteTask(id);
	}
}

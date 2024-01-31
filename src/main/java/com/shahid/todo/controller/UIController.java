package com.epam.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.todo.dto.Task;
import com.epam.todo.service.TaskService;

@Controller
@RequestMapping("/")
public class UIController {

	@Autowired
	@Qualifier("taskRepoService")
	TaskService taskService;

	@Value("redirect:/")
	private String homeRedirect;

	@GetMapping
	public String home(Model model) {
		model.addAttribute("appName", "TODO");
		model.addAttribute("completedTasks", taskService.getCompletedTasks());
		model.addAttribute("incompleteTasks", taskService.getIncompleteTasks());
		return "home";
	}

	@GetMapping("/task/{id}/complete")
	public String complete(@PathVariable("id") int id) {
		taskService.completeTask(id);
		return homeRedirect;
	}

	@GetMapping("/task/{id}/incomplete")
	public String incomplete(@PathVariable("id") int id) {
		taskService.incompleteTask(id);
		return homeRedirect;
	}

	@GetMapping("/task/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		taskService.deleteTask(id);
		return homeRedirect;
	}

	@GetMapping("/task/add")
	public String add() {
		return "addtask";
	}

	@PostMapping("/task/add")
	public String addTask(@RequestParam("name") String name, @RequestParam("description") String description) {
		taskService.addTask(name, description);
		return homeRedirect;
	}
	
	@GetMapping("/task/{id}/modify")
	public String modify(@PathVariable("id") int id, Model model) {
		model.addAttribute(taskService.getTask(id));
		return "modifytask";
	}
	
	@PostMapping("/task/modify")
	public String modifyTask(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam(name = "completed", defaultValue = "false") boolean completed) {
		taskService.modifyTask(id, name, description, completed);
		return homeRedirect;
	}
}

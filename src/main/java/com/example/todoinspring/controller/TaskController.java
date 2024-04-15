package com.example.todoinspring.controller;

import com.example.todoinspring.model.Task;
import com.example.todoinspring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTodo", new Task());
        return "index";
    }

    @PostMapping("/createNewTodo")
    public String createNewTodo(@ModelAttribute Task newTodo) {
        taskService.save(newTodo);
        return "redirect:/";
    }

    @GetMapping("/deleteTask")
    public String delete(@RequestParam Long taskId) {
        taskService.deleteById(taskId);
        return "redirect:/";
    }

    @GetMapping("/setStateToDoneAction")
    public String setStateToDone(@RequestParam Long taskId) {
        taskService.setStateToDone(taskId);
        return "redirect:/";
    }

    @GetMapping("/setStateToNotDoneAction")
    public String setStateToNotDone(@RequestParam Long taskId) {
        taskService.setStateToNotDone(taskId);
        return "redirect:/";
    }

    @GetMapping("/showDoneTasks")
    public String showDoneTasks(Model model) {
        List<Task> tasks = taskService.findByStatus(true);
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTodo", new Task());
        return "index";
    }

    @GetMapping("/showNotDoneTasks")
    public String showNotDoneTasks(Model model) {
        List<Task> tasks = taskService.findByStatus(false);
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTodo", new Task());
        return "index";
    }

    @GetMapping("removeDoneTasks")
    public String removeDoneTasks() {
        taskService.removeDoneTasks();
        return "redirect:/";
    }

    @PostMapping("/showUpdateTaskPage")
    public String editTask(@ModelAttribute Task task, @RequestParam Long taskId) {
        taskService.editTask(taskId, task);
        return "redirect:/";
    }
    @GetMapping("/sortTasksByTitle")
    public String sortByTitle(@RequestParam  String order , Model model) {
        List<Task> tasks = taskService.sortByTitle(order);
        model.addAttribute("tasks", tasks);
        model.addAttribute("sortOrder", order);
        model.addAttribute("newTodo", new Task());
        return "index";
    }
}

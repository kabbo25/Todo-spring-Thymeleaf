package com.example.todoinspring.controller;

import com.example.todoinspring.model.Task;
import com.example.todoinspring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTodo", new Task()); // Change "task" to "newTodo"
        return "index";
    }

    @PostMapping("/createNewTodo") // Change the endpoint to match th:action in the form
    public String createNewTodo(@ModelAttribute Task newTodo) { // Change parameter name to match th:object in the form
        taskRepository.save(newTodo);
        return "redirect:/";
    }

    @GetMapping("/deleteTask")
    public String delete(@RequestParam Long taskId) {
        taskRepository.deleteById(taskId);
        return "redirect:/";
    }
}

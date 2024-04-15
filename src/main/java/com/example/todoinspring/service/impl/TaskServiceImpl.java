package com.example.todoinspring.service.impl;

import com.example.todoinspring.model.Task;
import com.example.todoinspring.repository.TaskRepository;
import com.example.todoinspring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> sortByTitle(String order) {
        List<Task> tasks = taskRepository.findAll();
        if (order.equals("desc")) {
            tasks.sort(Comparator.comparing(Task::getName).reversed());
            return tasks;
        }
        tasks.sort(Comparator.comparing(Task::getName));
        return tasks;
    }

    @Override
    public void setStateToDone(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        task.setStatus(true);
        taskRepository.save(task);
    }

    @Override
    public void setStateToNotDone(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        task.setStatus(false);
        taskRepository.save(task);
    }

    @Override
    public List<Task> findByStatus(boolean status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public void removeDoneTasks() {
        List<Task> doneTasks = taskRepository.findByStatus(true);
        taskRepository.deleteAll(doneTasks);
    }

    @Override
    public void editTask(Long taskId, Task updatedTask) {
        Task taskToEdit = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        taskToEdit.setName(updatedTask.getName());
        taskToEdit.setDescription(updatedTask.getDescription());
        taskToEdit.setStatus(updatedTask.isStatus());
        taskRepository.save(taskToEdit);
    }
}

package com.example.todoinspring.service;

import com.example.todoinspring.model.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    void deleteById(Long taskId);
    void setStateToDone(Long taskId);
    void setStateToNotDone(Long taskId);
    List<Task> findByStatus(boolean status);
    void removeDoneTasks();
    void editTask(Long taskId, Task updatedTask);
    List<Task> findAll();
}

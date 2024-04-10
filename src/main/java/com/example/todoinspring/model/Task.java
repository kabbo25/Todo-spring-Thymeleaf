package com.example.todoinspring.model;

import jakarta.persistence.*;

@Entity
@Table(name="TODO")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private boolean status;

    // Constructors
    public Task() {
    }

    public Task(String name, String description, boolean status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

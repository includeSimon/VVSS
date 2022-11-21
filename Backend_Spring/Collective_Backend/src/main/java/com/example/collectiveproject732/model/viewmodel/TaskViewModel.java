package com.example.collectiveproject732.model.viewmodel;

import com.example.collectiveproject732.model.*;

public class TaskViewModel {
    public Long id;
    public Category category;
    public String name;

    public TaskViewModel(Long id, Category category, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
    }

    public static TaskViewModel fromTask(Task task) {
        return new TaskViewModel(task.id, task.category, task.name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

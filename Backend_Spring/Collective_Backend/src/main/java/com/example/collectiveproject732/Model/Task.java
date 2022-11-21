package com.example.collectiveproject732.Model;

import com.example.collectiveproject732.Model.viewmodel.TaskViewModel;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
public class Task {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public Category category;

    @NotNull
    public String name;

    @OneToMany(mappedBy = "task")
    List<UserTask> usersTasks;

    public TaskViewModel toTaskViewModel() {
        return new TaskViewModel(this.id, this.category, this.name);
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

    public List<UserTask> getUsersTasks() {
        return usersTasks;
    }

    public void setUsersTasks(List<UserTask> usersTasks) {
        this.usersTasks = usersTasks;
    }
}

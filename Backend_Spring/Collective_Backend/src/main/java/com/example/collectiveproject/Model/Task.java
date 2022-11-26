package com.example.collectiveproject.Model;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Task {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public Category category;

    @NotNull
    public String name;

    @NotNull
    public Status status;

    @NotNull
    public LocalDate targetDate;


    @NotNull
    public int reward;

    public String description;

    @OneToMany(mappedBy = "task")
    List<UserTask> usersTasks;

    public Task(Long id, Category category, String name, Status status, LocalDate targetDate, int reward, String description, List<UserTask> usersTasks) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.status = status;
        this.targetDate = targetDate;
        this.reward = reward;
        this.description = description;
        this.usersTasks = usersTasks;
    }

    public Task() {}


    public Task(Long id, Category category, String name, Status status, LocalDate targetDate, int reward, ArrayList<UserTask> userTasks) {
    }
}

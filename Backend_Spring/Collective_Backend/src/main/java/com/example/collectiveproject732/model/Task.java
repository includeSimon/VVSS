package com.example.collectiveproject732.model;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserTask> usersTasks = new ArrayList<>();
}

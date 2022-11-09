package com.example.collectiveproject732.Model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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

}

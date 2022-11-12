package com.example.collectiveproject732.Model;

import com.sun.istack.NotNull;
import lombok.Data;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserTask {
    @Id
    @NotNull
    public Long id;

    @NotNull
    public LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "task_id")
    public Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
}

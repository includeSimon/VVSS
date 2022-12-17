package com.example.collectiveproject.Model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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
package com.example.collectiveproject.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Task implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public String name;

    @NotNull
    public String description;

    @NotNull
    public Status status;

    @NotNull
    public Integer daysToCompleteTask;

    @NotNull
    public Integer rewardPoints;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tasks")
    public Category category;
    @OneToMany(mappedBy = "task")
    List<UserTask> usersTasks;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) && name.equals(task.name) && description.equals(task.description) && status == task.status && daysToCompleteTask.equals(task.daysToCompleteTask) && rewardPoints.equals(task.rewardPoints) && Objects.equals(usersTasks, task.usersTasks) && category.equals(task.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, daysToCompleteTask, rewardPoints, usersTasks, category);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", daysToCompleteTask=" + daysToCompleteTask +
                ", rewardPoints=" + rewardPoints +
                ", usersTasks=" + usersTasks +
                ", category=" + category +
                '}';
    }
}

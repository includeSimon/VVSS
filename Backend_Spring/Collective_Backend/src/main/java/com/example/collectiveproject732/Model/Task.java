package com.example.collectiveproject732.Model;

import com.example.collectiveproject.Model.Category;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
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

    @OneToMany(mappedBy = "task")
    List<UserTask> usersTasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tasks")
    public Category category;


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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    }


    public Integer getDaysToCompleteTask() {
        return daysToCompleteTask;
    }

    }

    public void setDaysToCompleteTask(Integer daysToCompleteTask) {
        this.daysToCompleteTask = daysToCompleteTask;

  	public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }


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

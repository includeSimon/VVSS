package com.example.collectiveproject.Model.DTO;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.Status;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class TaskDTO {

    private Long id;
    private String name;
    private String description;
    private Integer daysToCompleteTask;
    private Status status;
    private Category category;
    private Integer rewardPoints;

    public TaskDTO(Long id, String name, String description, Integer daysToCompleteTask, Status status, Category category, Integer reward) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.daysToCompleteTask = daysToCompleteTask;
        this.status = status;
        this.category = category;
        this.rewardPoints = reward;
    }
}

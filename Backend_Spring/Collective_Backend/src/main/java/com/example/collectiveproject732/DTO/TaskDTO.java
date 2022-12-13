package com.example.collectiveproject732.DTO;

import com.example.collectiveproject732.Model.Category;
import com.example.collectiveproject732.Model.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class TaskDTO {

    private Long id;
    private String name;
    private String description;
    private Integer daysToCompleteTask;
    private Status status;
    private String category;
    private Integer rewardPoints;
}

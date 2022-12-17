package com.example.collectiveproject.Model.DTO;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

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
    private LocalDate done;

}

package com.example.collectiveproject732.DTO;

import com.example.collectiveproject732.Model.Category;
import com.example.collectiveproject732.Model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class TaskDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDate targetDate;
    private Status status;
    private Category category;
}

package com.example.collectiveproject.Model.DTO;

import com.example.collectiveproject.Model.Color;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private Long id;
    private String nameCategory;
    private Color color;
}
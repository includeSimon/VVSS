package com.example.collectiveproject.Model.DTO;


import com.example.collectiveproject732.Model.Color;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {

    private String nameCategory;
    private Color color;
}
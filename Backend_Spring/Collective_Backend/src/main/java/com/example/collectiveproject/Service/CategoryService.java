package com.example.collectiveproject.Service;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.DTO.CategoryDTO;
import com.example.collectiveproject.Repository.CategoryRepository;
import com.example.collectiveproject.Model.DTO.CategoryDTO;
import com.example.collectiveproject732.DTO.TaskDTO;
import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject732.Model.Task;
import com.example.collectiveproject.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategoryByCategoryName(String nameCategory){
        return categoryRepository.findByNameCategory(nameCategory);
    }

    public List<CategoryDTO> getCategory(){
        return ((List<Category>) categoryRepository.findAll())
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public CategoryDTO convertEntityToDto(Category category) {
        return CategoryDTO.builder()
                .nameCategory(category.getNameCategory())
                .color(category.getColor())
                .build();
    }

    public Category convertDtoToEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .nameCategory(categoryDTO.getNameCategory())
                .color(categoryDTO.getColor())
                .build();
    }
}
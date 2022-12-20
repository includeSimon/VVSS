package com.example.collectiveproject.Service;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.DTO.CategoryDTO;
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

    public Category addCategory(Category category){
        return this.categoryRepository.save(category);
    }

    public Category updateCategory(Category categoryDetails) throws Exception {
        Category category = this.categoryRepository.findCategoryById(categoryDetails.getId());

        if(category == null){
            throw new Exception("Category should not be null!");
        }

        category.setNameCategory(categoryDetails.getNameCategory());
        category.setColor(categoryDetails.getColor());

        return categoryRepository.save(category);
    }


    public CategoryDTO convertEntityToDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .nameCategory(category.getNameCategory())
                .color(category.getColor())
                .build();
    }

    public Category convertDtoToEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .nameCategory(categoryDTO.getNameCategory())
                .color(categoryDTO.getColor())
                .build();
    }

}

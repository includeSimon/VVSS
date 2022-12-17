package com.example.collectiveproject.Service;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.DTO.CategoryDTO;
import com.example.collectiveproject.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategoryByCategoryName(String nameCategory){
        return categoryRepository.findByNameCategory(nameCategory);
    }

    public Iterable<Category> getCategory(){
        return this.categoryRepository.findAll();
    }
}

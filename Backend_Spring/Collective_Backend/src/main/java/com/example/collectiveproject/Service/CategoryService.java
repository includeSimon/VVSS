package com.example.collectiveproject732.Service;

import com.example.collectiveproject732.Model.Category;
import com.example.collectiveproject732.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategoryByCategoryName(String nameCategory){
        return categoryRepository.findByNameCategory(nameCategory);
    }
}

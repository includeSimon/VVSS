package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Model.DTO.CategoryDTO;
import com.example.collectiveproject.Model.DTO.CategoryDTO;
import com.example.collectiveproject.Model.DTO.TaskDTO;
import com.example.collectiveproject.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allCategory")
    public List<CategoryDTO> getAllTasks(){return categoryService.getCategory(); }

}
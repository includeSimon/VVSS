package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.DTO.CategoryDTO;
import com.example.collectiveproject.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allCategory")
    public List<CategoryDTO> getAllTasks(){return categoryService.getCategory(); }

    @RequestMapping(value="/addCategory", method = RequestMethod.POST)
    public CategoryDTO addTask(@RequestBody CategoryDTO categoryDTO) throws Exception {
        Category category = this.categoryService.convertDtoToEntity(categoryDTO);
        Category categoryCreated = this.categoryService.addCategory(category);
        return categoryService.convertEntityToDto(categoryCreated);
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.PUT)
    public CategoryDTO updateCategoryDTO(@RequestBody CategoryDTO categoryDTO) throws Exception{
        Category category = this.categoryService.convertDtoToEntity(categoryDTO);
        Category categoryUpdated = this.categoryService.updateCategory(category);
        return categoryService.convertEntityToDto(categoryUpdated);
    }
}

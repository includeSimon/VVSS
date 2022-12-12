package com.example.collectiveproject732.Repository;

import com.example.collectiveproject732.Model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findCategoryById(Long id);


    @Query(value = "select * from Category c where c.name_category=?", nativeQuery = true)
    Category findByNameCategory(@Param("nameCategory") String nameCategory);

}
